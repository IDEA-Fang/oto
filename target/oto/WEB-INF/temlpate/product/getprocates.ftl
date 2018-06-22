<!DOCTYPE html>
<html lang="en">

<#include "../common/header.ftl">

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>
                        类目
                    </th>
                    <th>
                        优先级
                    </th>

                    <th>
                        操作
                    </th>
                </tr>
                </thead>
                <tbody>

                <#list productCategories as productCategories>
                <tr class="success">
                    <td>${productCategories.productCategoryName}</td>
                    <td>${productCategories.priority}</td>
                    <td><a href="#">删除</a></td>
                </tr>
                </#list>

                <div>
                <from name = "productcategory" action="/addproductcategories">
                    类目:<input type="text" name="productcategory-name" />
                    优先级:<input type="text" name="productcategory-priority" />
                </from>
                </div>
                <button type="button" class="btn btn-default btn-primary">新增</button>
                <button type="button" class="btn btn-default btn-warning">修改</button>


                <tr>
                   <td></td>
                </tr>
                <tr class="success">
                    <td>
                        1
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        01/04/2012
                    </td>
                    <td>
                        Approved
                    </td>
                </tr>
                <tr class="error">
                    <td>
                        2
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        02/04/2012
                    </td>
                    <td>
                        Declined
                    </td>
                </tr>
                <tr class="warning">
                    <td>
                        3
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        03/04/2012
                    </td>
                    <td>
                        Pending
                    </td>
                </tr>
                <tr class="info">
                    <td>
                        4
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        04/04/2012
                    </td>
                    <td>
                        Call in to confirm
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
