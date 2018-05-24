//app.js
const APP_ID = 'wx87bdf521f0573ad0';//输入小程序appid
const APP_SECRET = '7633aa4a28bc49b77f1a289de916b95e';//输入小程序app_secret
App({
  d: {
   // hostUrl: 'https://m.dgclrj.com/tkSystem/',
     hostUrl:"http://192.168.0.152:8080/tkSystem/",
    //判断用户类型
     tkUserId: '152205226151757',//
     tkUserToken: '36c77990c8584246856dd7b56e0bb8ea',//
    tkUserName:'',
    tkUserHead:'',
    tkUserPhone: '13377786910',//
  },
  onLaunch: function () {
   
  },
  apirtnCallback: function (list){
     
  },
  globalData: {
    userInfo: null
  },
})
