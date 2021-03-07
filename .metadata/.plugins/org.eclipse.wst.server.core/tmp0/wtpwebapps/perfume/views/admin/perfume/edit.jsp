<%@page import="models.Picture"%>
<%@page import="models.Perfume"%>
<%@page import="models.CatPerfume"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
  <%@include file="/templates/admin/inc/header.jsp" %>
  <%@include file="/templates/admin/inc/sidebar.jsp" %>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">Quản lý nước hoa</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
              <li class="breadcrumb-item active">Nước hoa</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
           <div class="col-md-12">
                <h2>Sửa</h2>
            </div>
        </div>
        
        <%
        	if(request.getAttribute("product") != null){
        		Perfume product = (Perfume)request.getAttribute("product");
        		int catPF = product.getCatPer().getId();
        		%>
        <div class="row">
	        <div class="col-md-12">
	        	<div class="card">
			    	<div class="card-body">
			    		<div class="col-md-12">
			                <form role="form" method="post"id="form" action="<%=request.getContextPath()%>/admin/perfume/edit" enctype="multipart/form-data">
				                <div class="row">
				                	<div class="col-sm-6">
				                		<div class="form-group">
				                        	<input type="text" id="idPF" value="<%=product.getId()%>" name="idPF" class="form-control" />
					                    </div>
				                		<div class="form-group">
				                        	<label for="name">Tên nước hoa</label>
				                        	<input type="text" id="name" value="<%=product.getPerfumes()%>" name="name" class="form-control" />
					                    </div>
					                    <div class="row">
					                    	<div class="col-sm-4">
					                    	<%
					                    		if(request.getAttribute("catPFList") != null){
					                    			List<CatPerfume> catPFList = (List<CatPerfume>) request.getAttribute("catPFList");
					                    			if(catPFList.size() > 0){
					                    				%>
					                    		<div class="form-group">
							                        <label for="cat">Loại nước hoa</label>
							                        <select name="catPF" id="cars" class="form-control">
													  <option value="0">--- chọn ---</option>
													  <%
													  	for(CatPerfume objCatPF : catPFList){
													  		int idCatPF = objCatPF.getId();
													  		String nameCatPF = objCatPF.getCatPerfume();
													  		%>
													  	<option value="<%=idCatPF%>"
													  		<%
													  			if(catPF == idCatPF) out.print("selected");
													  		%>
													  	><%=nameCatPF %></option>
													  		<%
													  	}
													  %>
													</select>
							                    </div>
					                    				<%
					                    			}
					                    		}
					                    	%>
					                    		
					                    	</div>
					                    	<div class="col-sm-4">
					                    		<div class="form-group">
							                        <label for="code">Mã sản phẩm</label>
							                         <input type="text" id="code" value="<%=product.getCodePerfume()%>" name="codePF" class="form-control"/>
							                    </div>
					                    	</div>
					                    	<div class="col-sm-4">
					                    		<div class="form-group">
							                        <label for="brand">Thương hiệu</label>
							                         <input type="text" id="brand" value="<%=product.getBrand()%>" name="brand" class="form-control"/>
							                    </div>
					                    	</div>
					                    </div>
					                    <div class="form-group">
					                        <label for="desciption">Mô tả</label>
					                        <input type="text" id="desciption" value="<%=product.getDescription()%>" name="desciption" class="form-control" />
					                    </div>
					                    <div class="form-group">
					                        <label for="detail">Chi tiết</label><br></br>
					                        <textarea rows="9" cols="70" id="detail" class="form-group"name="detail"><%=product.getDetail()%></textarea>  
					                    </div>
					                    
				                	</div>
				                	<div class="col-sm-6">
				                		<div class="row">
				                			<div class="col-sm-3">
				                				<div class="form-group">
							                        <label for="made">Xuất xứ</label>
							                         <input type="text" id="made" value="<%=product.getMade()%>" name="made" class="form-control"/>
							                    </div>
				                			</div>
				                			<div class="col-sm-3">
				                				<div class="form-group">
							                        <label for="capacity">Dung tích</label>
							                         <input type="text" id="capacity" value="<%=product.getCapacity() %>" name="capacity" class="form-control"/>
							                    </div>
				                			</div>
				                			<div class="col-sm-3">
				                				<div class="form-group">
							                        <label for="quantum">Số lượng</label>
							                         <input type="text" id="quantum" value="<%=product.getAmount() %>" name="quantum" class="form-control"/>
							                    </div>
				                			</div>
				                			<div class="col-sm-3">
				                				<div class="form-group">
							                        <label for="money">Giá tiền</label>
							                         <input type="text" id="money" value="<%=product.getMoney() %>" name="money" class="form-control"/>
							                    </div>
				                			</div>
				                		</div>
					                    <div class="form-group">
					                   		<label for="picture">Hình ảnh</label>&emsp;
					                        <%
					                        	if(request.getAttribute("image") != null){
					                        		List<Picture> picList = (List<Picture>) request.getAttribute("image");
					                        		if(picList.size() > 0){
					                        			%>
					                        				<table class="table table-bordered">
															  <thead>
															    <tr>
															      <th scope="col">STT</th>
															      <th scope="col">Hình ảnh</th>
															      <th scope="col">Chức năng</th>
															    </tr>
															  </thead>
															  <tbody>
															  <%
															  	int i = 0;
					                        					for(Picture objPic : picList){
					                        						i++;
					                        						int id = objPic.getId();
					                        						String picture = objPic.getPicture();
					                        						String pic = request.getContextPath()+"/uploads/images/product/"+picture;
					                        						%>
					                        						<tr>
																      <th scope="row"><%=i %></th>
																      <td>
																      	<img alt="picture" src="<%=pic%>" style="width:200px;height:200px">
																      </td>
																      <td>
																      	<a image=<%=id%> title="" class="btn btn-primary del-pic"><i class="fa fa-trash" aria-hidden="true"></i>Sửa</a>
																      </td>
																    </tr>
					                        						<%
					                        					}
					                        					%>
															  </tbody>
															</table>
					                        			<%
					                        		}
					                        	}
					                        %>
					                    </div>
				                	</div>
				                    
				                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
				                </div>
			                </form>
			            </div>
			    	</div>
			  	</div>
	        </div>
        </div>
        		<%
        	}
        %>
        <!-- Main row -->
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <script>
$(document).on("click", ".del-cat", (function () {
	var image = $(this).attr("image");
	//lấy dữ liêu
	swal.fire({
        title: 'Bạn Có Chắc Chắn?',
        text: "Dữ Liệu Sẽ Không Thể Phục Hồi!",
        icon: 'warning',
        padding: '3em',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Đồng Ý, sửa!',
        cancelButtonText: 'Hủy bỏ',
        reverseButtons: true,
        customClass: null
    }).then((result) => {
    	  if (result.value) {
    		  $.ajax({
    			  	async: false,
    	            type: "get",
    	            url: "",
    	            data: {},
    	            success: function (data) {
    	            	if(data==false){
	    	            		Swal.fire(
   	    	                    'Thông Báo!',
   	    	                    'Bạn không thể sửa ảnh này.',
   	    	                    'danger'
   	    	                ).then(function () {
   	    	                    location.reload();
   	    	                })
    	            	}else{
   	            			Swal.fire(
	            			      'Thành công!',
	            			      'Bạn đã sửa thành công.',
	            			      'success'
	            			    ).then(function () {
   	    	                    location.reload();
   	    	                })
    	            	}
    	            },
    	            error: function (xhr, ajaxOptions, thrownError) {
    	                Swal.fire(
    	                    'Thông Báo!',
    	                    'Thao tác không thể thực hiện.',
    	                    'danger'
    	                ).then(function () {
    	                    location.reload();
    	                })
    	            }
    	        });
    		  } else if (result.dismiss === Swal.DismissReason.cancel) {
    		    swalWithBootstrapButtons.fire(
    		      'Thất bại',
    		      'Bạn Chưa thực hiện sửa',
    		      'error'
    		    )
    		  }
    		})
}))
</script>
  <%@include file="/templates/admin/inc/footer.jsp" %>