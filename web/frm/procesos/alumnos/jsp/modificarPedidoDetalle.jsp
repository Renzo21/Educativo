<%@page import="JSP_WEB_controlador.DetallesPedidosControlador"%>
<%@page import="JSP_WEB_modelo.Articulos"%>
<%@page import="JSP_WEB_modelo.Pedidos"%>
<%@page import="JSP_WEB_modelo.DetallesPedidos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    int cantidad_articulopedido = Integer.parseInt(request.getParameter("cantidad_articulopedido"));
   int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo")); 

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    DetallesPedidos detallepedido = new DetallesPedidos();
    detallepedido.setId_detallepedido(id_detallepedido);
    detallepedido.setCantidad_articulopedido(cantidad_articulopedido);
    
    Pedidos pedido = new Pedidos();
    pedido.setId_pedido(id_pedido);
    
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    
    detallepedido.setPedido(pedido);
    detallepedido.setArticulo(articulo);
      
    if (DetallesPedidosControlador.modificar(detallepedido)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>