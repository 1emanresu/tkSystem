// pages/task/taskdetails.js
var app = getApp();
Page({

  /**
   * 页面的初始数据 
   */
  data: {
    tk_plan_detail_id:"",
    tk_plan_detail_title:"",
    tk_plan_detail_target_achieve: "",
    tk_plan_detail_target: "",
    amongDate: "",
    tk_plan_detail_location: "",
    residuenum:"",//剩余未完成
    //客户跟进
    getClientInfoList: [],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.setData({
      tk_plan_detail_id: options.tk_plan_detail_id
    }) 
    that.thismonthbus();
    that.getClientInfoList();
  },
  /**
     * 任务详情
     */
  thismonthbus: function () {
    var that = this;
    console.log(" + that.data.tk_plan_detail_id " + that.data.tk_plan_detail_id);
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'employees/getPlanDetailByPlanId',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
        tk_plan_detail_id: that.data.tk_plan_detail_id,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var data = res.data.data;
     // console.log(" +f " + dat.tk_plan_detail_location);
        var num = data.tk_plan_detail_target - data.tk_plan_detail_target_achieve;
        that.setData({
          tk_plan_detail_title: data.tk_plan_detail_title,
          tk_plan_detail_target_achieve: data.tk_plan_detail_target_achieve,
          tk_plan_detail_target: data.tk_plan_detail_target,
          amongDate: data.amongDate,
          tk_plan_detail_location: data.tk_plan_detail_location,
          residuenum: num
        });   
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
  /**客户跟进 */
  getClientInfoList: function () {
    var that = this;
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'client/getClientInfo',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
        tkUserPhone: app.d.tkUserPhone,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var list = res.data.data;
        that.setData({
          getClientInfoList: list
        });
        console.log("  ^^^^  " + JSON.stringify(list));
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