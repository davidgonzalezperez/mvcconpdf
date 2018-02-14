package org.o7planning.springmvcshoppingcart.view;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.o7planning.springmvcshoppingcart.entity.Order;
import org.o7planning.springmvcshoppingcart.model.CartInfo;
import org.o7planning.springmvcshoppingcart.model.CartLineInfo;
import org.o7planning.springmvcshoppingcart.model.CustomerInfo;
import org.o7planning.springmvcshoppingcart.util.Utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class ItextPdfView extends AbstractITextPdfView {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document, PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

    	 
      @SuppressWarnings("unchecked")
     CartInfo cartInfo = Utils.getCartInSession(request);

        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[]{10, 60, 30});

       
        table.addCell(String.valueOf("Numero de orden:  "+cartInfo.getOrderNum()));
        table.addCell(String.valueOf("Cantidad Total:  "+cartInfo.getQuantityTotal()));   
        table.addCell(String.valueOf("Cliente "+cartInfo.getCustomerInfo().getName()));
        table.addCell(String.valueOf("Nº de contacto "+cartInfo.getCustomerInfo().getPhone()));
   
        
        
   for (CartLineInfo produ: cartInfo.getCartLines()  ){
            table.addCell("Codigo Producto:  "+produ.getProductInfo().getCode());
            table.addCell("Nombre Producto:  "+produ.getProductInfo().getName());

        }



        document.add(table);
    }

	private CustomerInfo CustomerInfo(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}