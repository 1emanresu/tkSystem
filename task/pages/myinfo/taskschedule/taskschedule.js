// pages/myinfo/taskschedule/taskschedule.js
var app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    datest: "",
    dates: "",
    scheduleList:[],
  },

  //显示拓客进度
  showtkschedule: function () {
    var that = this;
    //团队前三
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'client/getClientNumByChannel',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var list = res.data.data;
        console.log("  ff " + JSON.stringify(list));
        if (list == null) {
          list = [];
        }
        that.setData({
          scheduleList: list,
        })
      },
      fail: function () {
        wx.showToast({
          title: '服务器网络错误!',
          icon: 'loading',
          duration: 1500
        })
      }
    })
  },
  //条件显示拓客进度
  obyshowtkschedule: function () {
    var that = this;
    console.log("  sss " + that.data.dates + "   " + that.data.datest);
    //团队前三
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'client/getClientNumByChannel',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
        startTime: that.data.dates,
        endTime: that.data.datest
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var list = res.data.data;
        console.log("  aa " + JSON.stringify(list));
        if (list == null) {
          list = [];
        }
        that.setData({
          scheduleList: list,
        })
      },
      fail: function () {
        wx.showToast({
          title: '服务器网络错误!',
          icon: 'loading',
          duration: 1500
        })
      }
    })
  },
  //  点击日期组件确定事件  
  bindDateChange: function (e) {
    console.log(e.detail.value)
    var that = this;
    that.setData({
      dates: e.detail.value
    })
  },
  //  点击日期组件确定事件  
  bindDateChanget: function (e) {
    console.log(e.detail.value)
    var that=this;
    that.setData({
      datest: e.detail.value
    })
    if (that.data.dates == "" || that.data.dates==null){
      wx.showToast({
        title: '请选择开始日期!',
        icon: 'loading',
        duration: 1500
      })
    }else{
      that.obyshowtkschedule();
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.showtkschedule();
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