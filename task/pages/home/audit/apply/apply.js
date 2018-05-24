// pages/home/audit/apply/apply.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tk_good_apply_id: "",
    audit: "",

    starTime: "",
    tk_target_report_id: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var audit = options.audit;
    if (audit == 1) {
      var tk_good_apply_id = options.tk_good_apply_id;
      that.setData({
        tk_good_apply_id: tk_good_apply_id,
        audit: audit,
      });
    } else {
      var tk_target_report_id = options.tk_target_report_id;
      var starTime = options.starTime;
      that.setData({
        audit: audit,
        tk_target_report_id: tk_target_report_id,
        starTime: starTime
      });
    }
  },
  seav: function () {
    var that = this;
    console.log("  tk_good_apply_id  " + that.data.tk_good_apply_id);

    if (that.data.audit == 1) {
      wx.request({
        //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
        url: app.d.hostUrl + 'manager/putGoodsByManage',
        method: 'POST',
        data: {
          tkUserId: app.d.tkUserId,
          tkUserToken: app.d.tkUserToken,
          tk_good_apply_id: that.data.tk_good_apply_id,
          tkGoodApplyState: 0,   //1是成功  0是拒绝
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
          var list = res.data;
          wx.showToast({
            title: '审核成功',
            icon: 'loading',
            duration: 1500,
            success: function () {
              setTimeout(function () {
                //要延时执行的代码
                wx.switchTab({
                  url: '../../index',
                })
              }, 1500) //延迟时间 
            }
          })

          console.log("^^^VVV^^^^^" + JSON.stringify(list));
        },
        fail: function () {
          wx.showToast({
            title: '服务器网络错误!',
            icon: 'loading',
            duration: 1500
          })
        }
      });
    } else {
      console.log(" tkTargetReportId    " + that.data.tk_target_report_id);
      console.log(" tk_plan_detail_starttime " + that.data.starTime);

      wx.request({
        //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
        url: app.d.hostUrl + 'manager/putReportByManage',
        method: 'POST',
        data: {
          tkUserId: app.d.tkUserId,
          tkUserToken: app.d.tkUserToken,
          tkTargetReportId: that.data.tk_target_report_id,
          tk_report_state: 0,   //1是成功  0是拒绝
          tk_plan_detail_starttime: that.data.starTime
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
          var list = res.data;
          wx.showToast({
            title: '审核成功',
            icon: 'loading',
            duration: 1500,
            success: function () {
              console.log('haha');
              setTimeout(function () {
                //要延时执行的代码
                wx.switchTab({
                  url: '../../index',
                })
              }, 1500) //延迟时间 
            }
          })

          console.log("^^^VVV222^^^^^" + JSON.stringify(list));
        },
        fail: function () {
          wx.showToast({
            title: '服务器网络错误!',
            icon: 'loading',
            duration: 1500
          })
        }
      });
    }

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