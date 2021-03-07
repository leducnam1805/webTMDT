<%@page import="java.util.List"%>
<%@page import="models.CatPerfume"%>
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
            <h1 class="m-0">Loại nước hoa</h1>
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
                <h2>Sửa loại nước hoa</h2>
            </div>
        </div>
        <%
        	if(request.getAttribute("catPerfume") != null){
        		CatPerfume objCatPF = (CatPerfume) request.getAttribute("catPerfume");
        		int id = objCatPF.getId();
        		String name = objCatPF.getCatPerfume();
        		int parrent_id = objCatPF.getParrent_id();
        		%>
        <div class="row">
	        <div class="col-md-12">
	        	<div class="card">
			    	<div class="card-body">
			    		<div class="col-md-12">
			                <form role="form" method="post"id="form" action="<%=request.getContextPath()%>/admin/catperfume/edit">
			                	<div class="form-group">
			                        <input type="hidden" id="id" value="<%=id %>" name="id" class="form-control" />
			                    </div>
			                    <div class="form-group">
			                        <label for="name">Thương hiệu</label>
			                        <input type="text" id="name" value="<%=name %>" name="name" class="form-control" />
			                    </div>
			                    <%
			                    	if(request.getAttribute("catPFList") != null){
			                    		List<CatPerfume> catPFList = (List<CatPerfume>) request.getAttribute("catPFList");
			                    		if(catPFList.size() > 0){
			                    			%>
			                    				<div class="form-group">
			                    				<label for="name">Loại thương hiệu &emsp;</label>
							                        <select id="cars" name="catPF">
													  <option value="0">--- chọn ---</option>
													  <%
													  	for(CatPerfume objCatPFE : catPFList){
													  		int idCatPF = objCatPFE.getId();
													  		String nameCatPF = objCatPFE.getCatPerfume();
													  		%>
													  			<option value="<%=idCatPF%>"
													  				<%
													  					if(parrent_id == idCatPF)out.print("selected");
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
			                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
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
  <%@include file="/templates/admin/inc/footer.jsp" %>