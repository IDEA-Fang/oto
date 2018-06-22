$(function(){
    var initUrl = '/shopadmin/getshopinitinfo';
    var registerShopUrl= '/shopadmin/registershop';
    alert(initUrl);
    getShopInitInfo();
    function getShopInitInfo() {
        $.getJSON(initUrl,function (data) {
            if (data.success) {
                var tempHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item,index) {
                        tempHtml = tempHtml+'<option data-id="  ' +
                            item.shopCategoryId+' "> '+item.shopCategoryName+
                            '</option>';

                });
                data.areaList.map(function (item,index) {
                    tempAreaHtml = tempAreaHtml+'<option data-id="  ' +
                        item.areaId+' "> '+item.areaName+
                        '</option>';

                });
                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });
        $('#submit').click(function(){
           var shop={};
           shop.shopName = $('#shop-name').val();
           shop.shopAddress = $('#shop-address').val();
            shop.phone = $('#phone').val();
            shop.Desc = $('#shop-desc');
            shop.Category = {
                shopCategoryId:$('#shop-category').find('option')
                .not(function (){
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId:$('#area').find('option')
                .not(function() {
                    return !this.selected;
                }).data('id')
            };
            var captchaCodeActual = $('#y_captcha').val();
            var  shopImg = $('#shop-img')[0].files[0];

            var formData = new FormData();
            formData.append('shopImg',shopImg);

            formData.append('shopStr',JSON.stringify(shop));
            if(!captchaCodeActual){
                $.toast('请输入验证码！');
                return;
            }
            formData.append('captchaCodeActual',captchaCodeActual);
            $.ajax({
                url:registerShopUrl,
                type:'POST',
                data:{
                    shopName: shop.shopName = $('#shop-name').val(),
                    shopAddress: shop.shopAddress = $('#shop-address').val(),
                    shopPhone: shop.phone = $('#phone').val(),
                    shopDesc: shop.Desc = $('#shop-desc'),
                    shopCategory: shop.Category = {
                    shopCategoryId:$('#shop-category').find('option')
                    .not(function (){
                        return !this.selected;
                    }).data('id')
            },
                    shopArea: shop.area = {
                    areaId:$('#area').find('option')
                    .not(function() {
                        return !this.selected;
                    }).data('id')
            },
                    captchaCodeActual: captchaCodeActual = $('#y_captcha').val(),
                    shopImg: shopImg = $('#shop-img')[0].files[0],
                },
                cache: false,
                processData: false,
                contentType: false,
                error:function(request){//请求失败之后的操作
                   alert("提交失败1")
                },
                success:function (data) {
                    if (data.success){
                        $.toast('提交成功！1');
                    } else {
                        $.toast('提交失败2'+data.errMSg);
                    }
                    $('#y_captcha').click();
                    alert("提交成功2")
                }
            });
        });
    }
})