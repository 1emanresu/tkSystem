// pages/login/login.js
var code = require('../../utils/common.js')
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isShow: false,         //默认按钮1显示，按钮2不显示
    sec: "60",　　　　　　　　//设定倒计时的秒数
    userPhone:"",
    tkUserName:"",
    tkvail:""
  },
  //获取用户输入手机
  banduserPhone: function (e) {
    this.setData({
      userPhone: e.detail.value
    })
  },
  //获取用户输入的验证码
  banduservail: function (e) {
    this.setData({
      tkvail: e.detail.value
    })
  },
  //获取用户输入的用户名
  banduseruserName: function (e) {
    this.setData({
      tkUserName : e.detail.value
    })
  },
  sendCode: function () {
    var that = this;
    var phone = that.data.userPhone;
    if (phone = null || phone == ""){
      wx.showToast({
        title: '手机号不能是空!',
        icon: 'loading',
        duration: 1500
      })
      that.setData({
        sec:"60",
        isShow: false
      })
    }else{
      //发送验证码
      wx.request({
        //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
        url: app.d.hostUrl + 'user/getVail',
        method: 'POST',
        data: {
          tkUserId: app.d.tkUserId,
          tkUserToken: app.d.tkUserToken,
          tkUserPhone: that.data.userPhone
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
          var dataCode = res.data;
          console.log("  --dataCode -  " + JSON.stringify(dataCode));
    

        },
        fail: function () {
          wx.showToast({
            title: '服务器网络错误!',
            icon: 'loading',
            duration: 1500
          })
        }
      })
    }
  },
  /**
   * 绑定信息
   */
  bindinfo: function () {
    var that = this;
    var phone = that.data.userPhone;
    var tkUName = that.data.tkUserName;
    var tkvail = that.data.vail;
    if (phone = null || phone == "") {
      wx.showToast({
        title: '手机号不能是空!',
        icon: 'loading',
        duration: 1500
      })
    } else if (tkUName = null || tkUName == "") {
      wx.showToast({
        title: '用户名不能是空!',
        icon: 'loading',
        duration: 1500
      })
    } else if (tkvail = null || tkvail == "") {
      wx.showToast({
        title: '验证码不能是空!',
        icon: 'loading',
        duration: 1500
      })
    } else {
      //发送验证码
      wx.request({
        //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
        url: app.d.hostUrl + 'user/putPhoneAndName',
        method: 'POST',
        data: {
          tkUserId: app.d.tkUserId,
          tkUserToken: app.d.tkUserToken,
          tkUserPhone: that.data.userPhone,
          tkUserName:that.data.tkUserName,
          vail: that.data.tkvail
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
          var succCode = res.data;
          console.log("  --绑定成功信息 -  " + JSON.stringify(succCode));
          app.d.tkUserPhone = that.data.userPhone;
          app.d.tkUserName = that.data.tkUserName;
          wx.showToast({
            title: '绑定手机成功',
            icon: 'loading',
            duration: 1400
          })
          setTimeout(function () {
            wx.switchTab({
              url: '../../pages/myinfo/myinfo',
            });
          }, 1500) //延迟时间 这里是1秒  
        },
        fail: function () {
          wx.showToast({
            title: '服务器网络错误!',
            icon: 'loading',
            duration: 1500
          })
        }
      })
     }
  },
  getCode: function () {
    var _this = this;　　　　//防止this对象的混杂，用一个变量来保存
    var time = _this.data.sec　　//获取最初的秒数
    code.getCode(_this, time);　　//调用倒计时函数
    _this.sendCode();
  },

  //事件处理函数
  jumpIndex: function () {
    wx.switchTab({
      url: '/pages/home/index'
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // wx.switchTab({
    //   url: '../../pages/myinfo/myinfo',
    // });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})