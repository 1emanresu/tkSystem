// pages/home/task/staffChoice/staffChoice.js
var app = getApp();
Page({
  /**
   * 页面的初始数据 
   */
  data: {
    isAllSelect: false,
    staffList:[],
    //任务id
    tkPlanId:'',
    arr:[],
    selected:false,
  },

  //勾选事件处理函数 
  switchSelect: function (e) {
    // 获取item项的id，和数组的下标值 
    var Allprice = 0, i = 0;
    let id = e.target.dataset.id,
      index = parseInt(e.target.dataset.index);
      this.data.staffList[index].isSelect = !this.data.staffList[index].isSelect;
      if (this.data.staffList[index].isSelect){
        this.data.arr.push(this.data.staffList[index].tk_user_id);
      }else{
        this.data.arr.splice(index, 1);
      }
     this.setData({ 
        isAllSelect: this.data.isAllSelect,
        staffList: this.data.staffList,
     })
   
  },
  checkboxChange: function (e) {
    var that=this;
    //var newarray=[];
    if (e.detail.value != null && e.detail.value != ""){
      this.data.selected = true;
      console.log('携带value值为：', e.detail.value + " tkPlanId " + this.data.tkPlanId);
     // newarray.push(e.detail.value);
      this.data.arr = this.data.arr.concat(e.detail.value);
    }else{
      this.data.selected = false;
      var dataset = e.target.dataset;
      var Index = dataset.index;
      console.log("  Index  " + Index + "  dataset  " + dataset);
      //通过index识别要删除第几条数据，第二个数据为要删除的项目数量，通常为1
      this.data.arr.splice(Index,1);
      //渲染数据
      this.setData({
        arr: this.data.arr
      });
    } 
  },

  changeAllocation:function(){
    var arr = this.data.arr;
    var arr2=[];
    for (var i = 0; i < arr.length; i++) {
      if (arr2.indexOf(arr[i]) < 0) {
        arr2.push(arr[i]);
      }
    }
    console.log("  arr2   " + JSON.stringify(arr2));

    for(var i=0;i<arr2.length;i++){
      wx.request({
        //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
        url: app.d.hostUrl + 'manager/postPlanExecute',
        method: 'POST',
        data: {
          tkUserId: app.d.tkUserId,
          tkUserToken: app.d.tkUserToken,
          tkPlanId: this.data.tkPlanId,//任务id
          tkPlanUserId: arr2[i],//人员id
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
           console.log(JSON.stringify(res));
        },
        fail: function () {
          wx.showToast({
            title: '服务器网络错误!',
            icon: 'loading', 
            duration: 1500
          })
        },
        complete: () => {
          console.log(i);
          if (i == arr2.length) {        
            wx.showToast({
              title: '分配成功',
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
          } else {
           
          }
        }
      })
    }

  },
  //获取用户输入的用户名
  userNameInput: function (e) { 
    var that = this;
    console.log("  e.detail.value   " + e.detail.value);
    //人员选择
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'manager/getEmployeeByName',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
        tkUserName: e.detail.value
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
          staffList: list,
        })
        console.log(JSON.stringify(that.data.staffList));
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
    var that = this;
    that.setData({
      tkPlanId: options.tkPlanId
    }) 
    console.log("   32333  " + options.tkPlanId);
    //人员选择
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'manager/getEmployee',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken
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
          staffList: list,
        })
        console.log(res);
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
   * 条件查询 
   */
   selectStaff:function(){
    
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