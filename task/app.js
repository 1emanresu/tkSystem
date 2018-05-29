//app.js
const APP_ID = 'wx87bdf521f0573ad0';//输入小程序appid
const APP_SECRET = 'f606cf8f9dba67f08b20b3c0cd001464';//输入小程序app_secret
App({
  d: {
    //hostUrl: 'https://m.dgclrj.com/tkSystem/',
     hostUrl:"http://192.168.0.152:8080/tkSystem/",
    //判断用户类型
     tkUserId: '152205226151757',//152205226151757
      tkUserToken: '36c77990c8584246856dd7b56e0bb8ea',//36c77990c8584246856dd7b56e0bb8ea
    tkUserName:'',
    tkUserHead:'',
    tkUserPhone: '13377786910',//
  },
  onLaunch: function () {
   
  },
   
  globalData: {
    userInfo: null
  },
})
