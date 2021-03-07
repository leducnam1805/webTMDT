<%@page import="models.Contact"%>
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
            <h1 class="m-0">Quản lý liên hệ</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
              <li class="breadcrumb-item active">Liên hệ</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

	<%
		if(request.getAttribute("contactList") != null){
			List<Contact> contactList = (List<Contact>) request.getAttribute("contactList");
			if(contactList.size() > 0){
				%>
	<!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
	      <div class="col-sm-12">
            <form method="GET" action="<%=request.getContextPath()%>">
                 <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning" style="float:right" />
                 <input type="search" name="nameCat" value=""class="form-control" placeholder="Nhập tên khách hàng" style="float:right; width: 300px;" />
                 <div style="clear:both"></div>
             </form>
          </div><!-- /.col -->
        </div><br></br>
        <!-- /.row -->
        <table class="table table-striped table-bordered">
		  <thead>
		    <tr>
		      <th scope="col">STT</th>
		      <th scope="col">Tên khách hàng</th>
		      <th scope="col">Email</th>
		      <th scope="col">Phone</th>
		      <th scope="col">Messgae</th>
		      <th scope="col">Chức năng</th>
		    </tr>
		  </thead>
		  <tbody>
			<%
				int i = 0;
				for(Contact objContact : contactList){
					i++;
					String name = objContact.getName();
					String email = objContact.getEmail();
					String phone = objContact.getPhone();
					String message = objContact.getMessage();
					%>
						<tr>
					      <th scope="row"><%=i %></th>
					      <td><%=name %></td>
					      <td><%=email %></td>
					      <td><%=phone %></td>
					      <td><%=message %></td>
					      <td>
					      	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Xem</button>
					      </td>
					    </tr>
					<%
				}
			%>		  
		  </tbody>
		</table>
        <!-- Main row -->
        <nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		    <li class="page-item disabled">
		      <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Trang trước</a>
		    </li>
		    <li class="page-item"><a class="page-link" href="#">1</a></li>
		    <li class="page-item"><a class="page-link" href="#">2</a></li>
		    <li class="page-item"><a class="page-link" href="#">3</a></li>
		    <li class="page-item">
		      <a class="page-link" href="#">Trang tiếp</a>
		    </li>
		  </ul>
		</nav>
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
				<%
			}else{
				%>
					<div class="alert alert-danger" role="alert">
					  Không có dữ liệu
					</div>
				<%
			}
		}
	%>
    
    <!-- /.content -->
  </div>
  <!-- modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Lời nhắn của khách hàng</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">Tên khách hàng: </label>
            <input type="text" class="form-control" id="recipient-name">
          </div>
          <div class="form-group">
            <label for="email" class="col-form-label">Email: </label>
            <input type="text" class="form-control" id="email">
          </div>
          <div class="form-group">
            <label for="phone" class="col-form-label">Phone: </label>
            <input type="text" class="form-control" id="phone">
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">Message:</label>
            <textarea class="form-control" id="message-text"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- modal -->
  <!-- /.content-wrapper -->

  <%@include file="/templates/admin/inc/footer.jsp" %>