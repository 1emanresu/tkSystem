// pages/home/audit/materialInfo/materialInfo.js
var dateTimePicker = require('../../../../utils/dateTimePicker.js');
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tk_plan_detail_title:"",
    tk_plan_detail_start:"",
    tk_target_report_remark:"",
    tk_target_report_id:"",
    starTime:"",
    dateTimeArray: null,
    dateTime: null,
  },
  changeDateTime(e) {
    this.setData({ dateTime: e.detail.value });
  },
  changeDateTimeColumn(e) {
    var arr = this.data.dateTime, dateArr = this.data.dateTimeArray;
    arr[e.detail.column] = e.detail.value;
    dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);
    this.setData({
      dateTimeArray: dateArr,
      dateTime: arr
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    // 获取完整的年月日 时分秒，以及默认显示的数组
    var obj = dateTimePicker.dateTimePicker(that.data.startYear, that.data.endYear);
    var obj1 = dateTimePicker.dateTimePicker(that.data.startYear, that.data.endYear);
    // 精确到分的处理，将数组的秒去掉
    var lastArray = obj1.dateTimeArray.pop();
    var lastTime = obj1.dateTime.pop();

    that.setData({
      dateTime: obj.dateTime,
      dateTimeArray: obj.dateTimeArray,
    });
    var starTime = that.data.dateTimeArray[0][that.data.dateTime[0]] + "-" + that.data.dateTimeArray[1][that.data.dateTime[1]] + "-" + that.data.dateTimeArray[2][that.data.dateTime[2]] + " " + that.data.dateTimeArray[3][that.data.dateTime[3]] + ":" + that.data.dateTimeArray[4][that.data.dateTime[4]] + ":" + that.data.dateTimeArray[5][that.data.dateTime[5]];

    var tkTargetReportId = options.tkTargetReportId;
    console.log("  tkTargetReportId  " + tkTargetReportId);
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'manager/getReportByManageDetail',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
        tkTargetReportId: tkTargetReportId,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var data = res.data.data;
        that.setData({
          tk_plan_detail_title: data.tk_plan_detail_title,
          tk_plan_detail_start: data.tk_plan_detail_start,
          tk_target_report_remark: data.tk_target_report_remark,
          tk_target_report_id: data.tk_target_report_id,
          starTime: starTime
        });
        console.log("%%%%%%~~ " + data.tk_target_report_id);
        console.log("%%%%%%~~ " + JSON.stringify(data));
      },
      fail: function () {
        wx.showToast({
          title: '服务器网络错误!',
          icon: 'loading',
          duration: 1500
        })
      }
    });
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