/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yolanda.tablafinal;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a047692839q
 */
public class calculador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            Gson gson = new Gson();
            ArrayList dataJason = new ArrayList();

            Random r = new Random();
            int Low = 500;
            int High = 3000;
            int Result = r.nextInt(High - Low) + Low;
            Thread.sleep(Result);

            String operando1 = request.getParameter("op1");
            String operando2 = request.getParameter("op2");
            String operacion = request.getParameter("ope");
            String expReg = "^-?[0-9]+([.][0-9]+)?$";

            String error = "";

            if (operando1.equals("") || operando2.equals("")) {
                error = "Rellene los campos vacíos.";
                response.setStatus(500);

                response.getWriter().append(gson.toJson(error));
               
                return;
            }

            if (!operando1.matches(expReg) || !operando2.matches(expReg)) {
                error = "Introduce únicamente números.";
                response.setStatus(500);

                response.getWriter().append(gson.toJson(error));

                return;
            }

            if (("suma").equals(operacion)) {
                Double suma = parseDouble(operando1) + parseDouble(operando2);
                dataJason.add(suma);
            }
            if (("resta").equals(operacion)) {
                Double resta = parseDouble(operando1) - parseDouble(operando2);
                dataJason.add(resta);
            }
            if (("multiplica").equals(operacion)) {
                Double multiplica = parseDouble(operando1) * parseDouble(operando2);
                dataJason.add(multiplica);
            }
            if (("divide").equals(operacion)) {
                Double divide = parseDouble(operando1) / parseDouble(operando2);
                dataJason.add(divide);
            }

            response.getWriter().append(gson.toJson(dataJason));

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
