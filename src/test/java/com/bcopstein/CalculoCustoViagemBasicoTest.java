package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculoCustoViagemBasicoTest {
    private Roteiro roteiro1;
    private Passageiro passageiro1;
    private List<Bairro> bairros;

    @BeforeEach
    public void setup(){
        bairros = new ArrayList<>();
        bairros.add(new Bairro("teste", new Area(new Ponto(1, 4), new Ponto(4, 1)), 20));
        bairros.add(new Bairro("teste2", new Area(new Ponto(4, 4), new Ponto(7, 1)), 30));
        bairros.add(new Bairro("teste3", new Area(new Ponto(4, 7), new Ponto(7, 4)), 10));
        roteiro1 = new Roteiro(bairros.get(0), bairros.get(1), bairros);
        passageiro1 = Passageiro.passageiroExistente("000.000.000-99", "teste", 10, 1);
    }

    @Test
    public void testaCalculoCustoBasico(){
        CalculoCustoViagemBasico ccvv = new CalculoCustoViagemBasico();
        ccvv.definePassageiro(passageiro1);
        ccvv.defineRoteiro(roteiro1);
        double valorEsperado = 50; // 20 + 30
        double valor = ccvv.calculoCustoBasico();
        assertEquals(valorEsperado, valor);
    }
}
