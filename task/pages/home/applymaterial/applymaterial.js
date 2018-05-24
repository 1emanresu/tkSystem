// pages/home/applymaterial/applymaterial.js
var app = getApp();
Page({

  /**
   * 页面的初始数据 
   */
  data: {
    num:1,
    // 使用data数据对象设置样式名  
    minusStatus: 'disabled' ,
    //任务类型名
    array: [],
    //任务类型id
    arrvaule: [], 
    index: 0,
    //任务类型id
    taskId:""
  },
  listenerPickerSelected: function (e) {
    //改变index值，通过setData()方法重绘界面
    console.log(" 任务 " + this.data.arrvaule[e.detail.value]);
    this.setData({
      index: e.detail.value,
      taskId: this.data.arrvaule[e.detail.value]
    });
  }, 
  formSubmit: function (e) {
    const that = this;
    if (e.detail.value.remark != null && e.detail.value.remark != "" && e.detail.value.tkGoodName != null && e.detail.value.tkGoodName != "") {
      wx.request({
        //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
        url: app.d.hostUrl + 'employees/postApplicationMaterials',
        method: 'POST',
        data: {
          tkUserId: app.d.tkUserId,
          tkUserToken: app.d.tkUserToken,
          tkGoodName: e.detail.value.tkGoodName ,
          remark: e.detail.value.remark,
          tkPlanId: that.data.taskId,
          tkGoodApplyGoodAmount:that.data.num
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
          var list = res.data;
          wx.showToast({
            title: list.msg,
            icon: 'loading',
            duration: 1500,
            success: function () {
              setTimeout(function () {
                //要延时执行的代码
                wx.switchTab({
                  url: '../index',
                })
              }, 1500) //延迟时间 
            }
          })
          console.log("  @@@@@@  " + JSON.stringify(list));
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
      wx.showToast({
        title: '请认真填写信息',
        icon: 'loading',
        duration: 1500
      })
    }

  },
  /* 点击减号 */
  bindMinus: function () {
    var num = this.data.num;
    // 如果大于1时，才可以减  
    if (num > 1) {
      num--;
    }
    // 只有大于一件的时候，才能normal状态，否则disable状态  
    var minusStatus = num <= 1 ? 'disabled' : 'normal';
    // 将数值与状态写回  
    this.setData({
      num: num,
      minusStatus: minusStatus
    });
  },
  /* 点击加号 */
  bindPlus: function () {
    var num = this.data.num;
    // 不作过多考虑自增1  
    num++;
    // 只有大于一件的时候，才能normal状态，否则disable状态  
    var minusStatus = num < 1 ? 'disabled' : 'normal';
    // 将数值与状态写回  
    this.setData({
      num: num,
      minusStatus: minusStatus
    });
  },
  /* 输入框事件 */
  bindManual: function (e) {
    var num = e.detail.value;
    // 将数值与状态写回  
    this.setData({
      num: num
    });
  }  ,
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.taskshow();
  },
  taskshow:function(){
    var that = this;
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'employees/getPlanByUserId',
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
        for (var i = 0; i < list.length; i++) {
          that.data.array.push(list[i].tk_plan_detail_title);
          that.data.arrvaule.push(list[i].tk_plan_detail_id);
        }
        console.log("  List11111111  " + JSON.stringify(list));
        that.setData({
          array: that.data.array,
          arrvaule: that.data.arrvaule,
          taskId: that.data.arrvaule[0]
        });
        console.log("  @@@@@@  "+JSON.stringify(res.data));
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