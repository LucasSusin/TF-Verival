package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;

import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagemRelampago;
import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagemVerao;
import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;

import com.bcopstein.casosDeUso.Politicas.ICalculoCustoViagem;
import com.bcopstein.casosDeUso.Politicas.CustoViagem;
import org.junit.jupiter.api.Test;

public class CustoViagemTest {
    @Test
    public void testaCustoViagem(){
        ICalculoCustoViagem ccv = mock(CalculoCustoViagemBasico.class);
        when(ccv.custoViagem()).thenReturn(35.0);
        CustoViagem cv = new CustoViagem(ccv);
        double custoObs = cv.custoViagem(null, null);
        double custoEsp = 35;
        assertEquals(custoEsp,custoObs,0.001);
    }

    @Test
    public void testaCustoViagemIntegradoVerao(){
        List<Bairro> bairros = new ArrayList<>();
        bairros.add(new Bairro("teste", new Area(new Ponto(1, 4), new Ponto(4, 1)), 20));
        bairros.add(new Bairro("teste2", new Area(new Ponto(4, 4), new Ponto(7, 1)), 30));
        bairros.add(new Bairro("teste3", new Area(new Ponto(4, 7), new Ponto(7, 4)), 10));
        Roteiro roteiro1 = new Roteiro(bairros.get(0), bairros.get(1), bairros);
        Roteiro roteiro2 = new Roteiro(bairros.get(0), bairros.get(2), bairros);
        Passageiro passageiro1 = Passageiro.passageiroExistente("000.000.000-99", "teste", 10, 1);
        Passageiro passageiro2 = Passageiro.passageiroExistente("000.000.000-99", "teste", 10, 2);
        CalculoCustoViagemVerao cv = new CalculoCustoViagemVerao();
        cv.definePassageiro(passageiro1);
        cv.defineRoteiro(roteiro1);
        double custoObs = cv.custoViagem();
        double custoEsp = 5;
        assertEquals(custoEsp,custoObs,0.001);
        CalculoCustoViagemVerao cv2 = new CalculoCustoViagemVerao();
        cv2.definePassageiro(passageiro2);
        cv2.defineRoteiro(roteiro2);
        double custoObs2 = cv2.custoViagem();
        double custoEsp2 = 54;
        assertEquals(custoEsp2,custoObs2,0.001);
    }


    @Test
    public void testaCustoViagemIntegradoRelampago(){
        List<Bairro> bairros = new ArrayList<>();
        bairros.add(new Bairro("teste", new Area(new Ponto(1, 4), new Ponto(4, 1)), 20));
        bairros.add(new Bairro("teste2", new Area(new Ponto(4, 4), new Ponto(7, 1)), 30));
        bairros.add(new Bairro("teste3", new Area(new Ponto(4, 7), new Ponto(7, 4)), 10));
        bairros.add(new Bairro("teste4", new Area(new Ponto(7, 10), new Ponto(10, 7)), 10));
        Roteiro roteiro1 = new Roteiro(bairros.get(0), bairros.get(1), bairros);
        Roteiro roteiro2 = new Roteiro(bairros.get(0), bairros.get(3), bairros);
        Passageiro passageiro1 = Passageiro.passageiroExistente("000.000.000-99", "teste", 350, 35);
        Passageiro passageiro2 = Passageiro.passageiroExistente("000.000.000-99", "teste", 10, 2);
        CalculoCustoViagemRelampago cv = new CalculoCustoViagemRelampago();
        cv.definePassageiro(passageiro1);
        cv.defineRoteiro(roteiro1);
        double custoObs = cv.custoViagem();
        double custoEsp = 47.5;
        assertEquals(custoEsp,custoObs,0.001);
        CalculoCustoViagemRelampago cv2 = new CalculoCustoViagemRelampago();
        cv2.definePassageiro(passageiro2);
        cv2.defineRoteiro(roteiro2);
        double custoObs2 = cv2.custoViagem();
        double custoEsp2 = 66.5;
        assertEquals(custoEsp2,custoObs2,0.001);
    }
}
