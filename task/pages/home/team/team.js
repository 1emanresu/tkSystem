// pages/home/team/team.js
var app = getApp();
Page({
  /**
   * 页面的初始数据 
   */
  data: {  
    //前三数据
    teamListThree:[], 
    //所有人员
    Listdata:[],
    testList:[],
 },

  selectTeam: function () {
    var that = this;
    //团队前三
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'manager/getPlanThree',
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
        for(var i=0;i<list.length;i++){
            if(i<2){
              console.log(list[i].No);
              that.data.testList.push(list[i].No);
            }else{
              console.log("  -----     "+list[i].No);
            }
        }
        console.log("  ----- 2    " + that.data.testList);
        that.setData({
          teamListThree: list,    
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
  selectTeamT: function () {
    var that = this;
    //人员信息
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'manager/getPlanThreeH',
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
        console.log(JSON.stringify(list));
        that.setData({
          Listdata: list,
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

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.selectTeam();
    this.selectTeamT();
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