<%@page import="models.CatPerfume"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
  <%@include file="/templates/admin/inc/header.jsp" %>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
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
                <h2>Thêm nước hoa</h2>
            </div>
        </div>
        <%
        	if(request.getAttribute("err") != null){
        		%>
        			<div class="alert alert-danger" role="alert">
						  ${err}
					</div>
        		<%
        	}
        %>
        <div class="row">
	        <div class="col-md-12">
	        	<div class="card">
			    	<div class="card-body">
			    		<div class="col-md-12">
			                <form role="form" method="post"id="form" action="<%=request.getContextPath()%>/admin/perfume/add" enctype="multipart/form-data">
				                <div class="row">
				                	<div class="col-sm-6">
				                		<div class="form-group">
				                        	<label for="name">Tên nước hoa</label>
				                        	<input type="text" id="name" value="" name="name" class="form-control" />
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
													  	<option value="<%=idCatPF%>"><%=nameCatPF %></option>
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
							                         <input type="text" id="code" value="" name="codePF" class="form-control"/>
							                    </div>
					                    	</div>
					                    	<div class="col-sm-4">
					                    		<div class="form-group">
							                        <label for="brand">Thương hiệu</label>
							                         <input type="text" id="brand" value="" name="brand" class="form-control"/>
							                    </div>
					                    	</div>
					                    </div>
					                    <div class="form-group">
					                        <label for="desciption">Mô tả</label>
					                        <input type="text" id="desciption" value="" name="desciption" class="form-control" />
					                    </div>
					                    <div class="form-group">
					                        <label for="detail">Chi tiết</label><br></br>
					                        <textarea rows="9" cols="61" id="detail" class="form-group"name="detail"></textarea>  
					                    </div>
					                    
				                	</div>
				                	<div class="col-sm-6">
				                		<div class="row">
				                			<div class="col-sm-3">
				                				<div class="form-group">
							                        <label for="made">Xuất xứ</label>
							                         <input type="text" id="made" value="" name="made" class="form-control"/>
							                    </div>
				                			</div>
				                			<div class="col-sm-3">
				                				<div class="form-group">
							                        <label for="capacity">Dung tích</label>
							                         <input type="text" id="capacity" value="" name="capacity" class="form-control"/>
							                    </div>
				                			</div>
				                			<div class="col-sm-3">
				                				<div class="form-group">
							                        <label for="quantum">Số lượng</label>
							                         <input type="text" id="quantum" value="" name="quantum" class="form-control"/>
							                    </div>
				                			</div>
				                			<div class="col-sm-3">
				                				<div class="form-group">
							                        <label for="money">Giá tiền</label>
							                         <input type="text" id="money" value="" name="money" class="form-control"/>
							                    </div>
				                			</div>
				                		</div>
					                    <div class="form-group">
					                        <label for="picture">Hình ảnh</label>&emsp;
					                         <!-- <input type="file" id="file-input" multiple name="picture"/><br></br> -->
					                          <%-- <img class="blah"width="200px" height="200px" src="<%=request.getContextPath()%>/templates/admin/images/noimage.gif" alt="no-image"/> --%>
					                    	<input type="file" multiple id="gallery-photo-add" name="picture">
					                    	<div class="gallery"></div>
					                    </div>
				                	</div>
				                    
				                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
				                </div>
			                </form>
			            </div>
			    	</div>
			  	</div>
	        </div>
        </div>
        <!-- Main row -->
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <script>
  $(function() {
	    // Multiple images preview in browser
	    var imagesPreview = function(input, placeToInsertImagePreview) {

	        if (input.files) {
	            var filesAmount = input.files.length;

	            for (i = 0; i < filesAmount; i++) {
	                var reader = new FileReader();

	                reader.onload = function(event) {
	                    $($.parseHTML('<img style="width:220px;height:200px;padding:3px;">')).attr('src', event.target.result).appendTo(placeToInsertImagePreview);
	                }

	                reader.readAsDataURL(input.files[i]);
	            }
	        }

	    };

	    $('#gallery-photo-add').on('change', function() {
	        imagesPreview(this, 'div.gallery');
	    });
	});
  </script>
  <!-- /.content-wrapper -->
  <%@include file="/templates/admin/inc/footer.jsp" %>