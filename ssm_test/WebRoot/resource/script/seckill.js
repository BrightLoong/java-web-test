var seckill = {
	URL : {
		now : function() {
			return seckill.basePath() + '/seckill/time/now';
		},
		exposer : function(seckillId) {
			return seckill.basePath() + '/seckill/' + seckillId + '/exposer'; 
		},
		execution : function(seckillId, md5) {
			return  seckill.basePath() + '/seckill/' + seckillId + '/' + md5 + '/execution';
		}
	},
	handleSeckillkill : function(seckillId, node){
		node.hide().html('<button class="btn btn-primary brn-lg" id="killBtn">开始秒杀</button>');
		$.post(seckill.URL.exposer(seckillId),{},function(result){
			if (result && result['success']) {
				var exposer = result['data'];
				if (exposer['exposed']) {
					var md5 = exposer['md5'];
					var killUrl = seckill.URL.execution(seckillId, md5);
					$('#killBtn').one('click', function(){
						$(this).addClass('disabled');
						$.post(killUrl, {}, function(result){
							if (result && result['success']) {
								var killResult = result['data'];
								//var state = killResult['state'];
								var stateInfo = killResult['stateInfo'];
								node.html('<span class="label label-success">' + stateInfo + '</span>');
							}
						});
					});
					node.show();
				} else {
					var nowTime = exposer['now'];
					var startTime = exposer['start'];
					var endTime = exposer['end'];
					seckill.countdown(seckillId, nowTime, startTime, endTime);
				}
			} else {
				console.log(result);
			}
		});
	},
	validatePhone : function(phone){
		if(phone && phone.length == 11 && !isNaN(phone)) {
			return true;
		} else {
			return false;
		}
	},
	basePath : function(){
		 var location = (window.location+'').split('/'); 
		 var basePath = location[0]+'//'+location[2]+'/'+location[3];
		 return basePath;
	},
	countdown:function(seckillId, nowTime, startTime, endTime){
		var seckillBox = $('#seckill-box');
		if (nowTime > endTime) {
			seckillBox.html('秒杀结束！');
		} else if (nowTime < startTime) {
			var killTime = new Date(startTime + 1000);
			console.log(killTime);
			seckillBox.countdown(killTime,function(event){
				var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
				console.log(format);
				seckillBox.html(format);
			}).on('finish.countdown', function(){
				//计时完成，显示秒杀
			});
			//seckillBox.countdown({until: killTime});
		} else {
			//秒杀开始
			seckill.handleSeckillkill(seckillId, seckillBox);
		}
	},
	detail : {
		init : function(parame) {
			//用戶手机验证和登录，计时交互
			var killPhone = $.cookie('killPhone');
			var startTime = parame['startTime'];
			var endTime = parame['endTime'];
			var seckillId = parame['seckillId'];
			//验证手机号
			if (!seckill.validatePhone(killPhone)) {
				var killPhoneModal = $('#killPhoneModal');
				killPhoneModal.modal({
					show:true,
					backdrop:'static',
					keyboard:false
				});			
				$('#killPhoneBtn').click(function(){
					var inputPhone = $('#killPhoenKey').val();
					if (seckill.validatePhone(inputPhone)) {
						$.cookie('killPhone',inputPhone,{expires:7,paht:'/seckill'});
						window.location.reload();
					} else {
						$('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
					}
				});
			}
			
			//计时交互
			$.get(seckill.URL.now(), {}, function (result) {
				if (result && result['success']) {
					var nowTime = result['data'];
					seckill.countdown(seckillId, nowTime, startTime, endTime);
				} else {
					alert('返回失败');
				}
			});
		}
	
	}
};
