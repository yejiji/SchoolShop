/**
 * Created by z1271 on 2019/3/27.
 */
$(function () {
   var shopId = getQueryString('shopId');
   var shopInfoUrl = '/shopadmin//getshopmangagementinfo?shopId='+shopId;
   $.getJSON(shopInfoUrl,function (data) {
       if(data.redirect){
           window.location.href = data.url;
       }else {
           if(data.shopId!=undefined&&data.shopId!=null){
               shopId = data.shopId;
           }
           $('#shopInfo').attr('href','/shopadmin/shopoperation?shopId='+shopId);
       }
   })
});
