/* package com.rimoldi.test;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import com.rimoldi.model.Inquilino;
import com.rimoldi.services.DAOs.InquilinoDAO;
import com.rimoldi.controller.InquilinoController;
import spark.Request;
import spark.Response;

public class InquilinoControllerTest {

    private InquilinoController inquilinoController;
    private InquilinoDAO inquilinoDAO;
    private Request req;
    private Response res;

    @BeforeEach
    public void setUp() {
        inquilinoDAO = mock(InquilinoDAO.class);
        inquilinoController = new InquilinoController();
        req = mock(Request.class);
        res = mock(Response.class);
    }

    @Test
    public void testPostInquilinoSuccess() throws Exception {
        when(req.queryParams("nombre")).thenReturn("John Doe");
        when(req.queryParams("direccion")).thenReturn("123 Main St");
        when(req.queryParams("fecha_nacimiento")).thenReturn("1990-01-01");
        when(req.queryParams("telefono")).thenReturn("1234567890");
        when(req.queryParams("email")).thenReturn("john.doe@example.com");
        when(req.queryParams("ocupacion")).thenReturn("Engineer");
        when(req.queryParams("ultimo_sueldo")).thenReturn("5000");
        when(req.queryParams("fecha_recibo")).thenReturn("2023-01-01");

        String result = (String) inquilinoController.postInquilino.handle(req, res);

        verify(res).type("application/json");
        verify(res).status(201);
        verify(inquilinoDAO).insertInquilino(any(Inquilino.class));
        assertEquals("Inquilino creado", result);
    }

    @Test
    public void testPostInquilinoFailure() throws Exception {
        when(req.queryParams("nombre")).thenReturn(null);

        String result = (String) inquilinoController.postInquilino.handle(req, res);

        verify(res).type("application/json");
        verify(res).status(500);
        assertEquals("Error al crear inquilino", result);
    }
} */