// pages/task/task.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    winHeight: "",//窗口高度
    currentTab: 0, //预设当前项的值
    scrollLeft: 0, //tab标题的滚动条位置

    //全部任务
    alltaskList: [],
    latitude: "",
    longitude: ""
  },
  // 滚动切换标签样式
  switchTab: function (e) {
    /**
     * 刘勇
     */
    var that = this;
    var longitude = that.data.longitude;
    var latitude = that.data.latitude;
    var pdkey = e.detail.current;//获取当面页面的唯一key值
    if (pdkey==0){//切换页面:全部任务
      that.allTask();
    } else if (pdkey == 1) {//切换页面:距离最近
      that.shortDistanceTask(longitude, latitude);
    } else if (pdkey == 2) {//切换页面:剩余时间
      that.timeRemainingTask();
    } else if (pdkey == 3) {//切换页面:筛选
      that.timeRemainingTask();
    }
    this.setData({
      currentTab: e.detail.current
    });
    this.checkCor();
  },
  // 点击标题切换当前页时改变样式
  swichNav: function (e) {
    var cur = e.target.dataset.current;
    if (this.data.currentTaB == cur) { return false; }
    else {
      this.setData({
        currentTab: cur
      })
    }
  },
  //判断当前滚动超过一屏时，设置tab标题滚动条。
  checkCor: function () {
    if (this.data.currentTab > 4) {
      this.setData({
        scrollLeft: 300
      })
    } else {
      this.setData({
        scrollLeft: 0
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载 
   */
  onLoad: function (options) {
    var that = this;
    //  高度自适应
    wx.getSystemInfo({
      success: function (res) {
        var clientHeight = res.windowHeight,
          clientWidth = res.windowWidth,
          rpxR = 750 / clientWidth;
        var calc = clientHeight * rpxR - 85;;
        //console.log(calc)
        that.setData({
          winHeight: calc
        });
      }
    });
    that.allTask();
    that.getcurrentpostion();
  },
  /**
   * 获取当前位置信息
   */
  getcurrentpostion: function () {
    var that = this;
    wx.getLocation({
      type: 'gcj02',
      altitude: true,
      success: function (res) {
        console.log("  res @ " + JSON.stringify(res));
        var latitude = res.latitude
        var longitude = res.longitude
        that.setData({
          latitude: latitude,
          longitude: longitude
        })
      }
    })
  },
  /**
   * 刘勇
   * 剩余时间 */
  timeRemainingTask: function () {
    var that = this;
    //客户信息
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'manager/getPlanByTime',
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

        if (list == null) {
          list = [];
        }
        that.setData({
          alltaskList: list
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

  /**距离最近 */
  shortDistanceTask: function (longitude, latitude) {
    var that = this;
    //客户信息
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'manager/getPlanByDistance',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
        longitude: longitude,
        latitude: latitude
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var list = res.data.data;

        if (list == null) {
          list = [];
        }
        that.setData({
          alltaskList: list
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
/**end********************************************************************** */
  /**全部任务 */
  allTask:function(){
    var that = this;
    //客户信息
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'manager/getPlan',
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
        
        if (list == null) {
          list = [];
        }
        that.setData({
          alltaskList : list
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
  footerTap: app.footerTap,

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