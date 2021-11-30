package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoteiroTest {
    private List<Bairro> bairros;

    @BeforeEach
    public void setup(){
        bairros = new ArrayList<>();
        bairros.add(new Bairro("Centro", new Area(new Ponto(1, 4), new Ponto(4, 1)), 20));
        bairros.add(new Bairro("Partenon", new Area(new Ponto(4, 4), new Ponto(7, 1)), 30));
        bairros.add(new Bairro("Teste", new Area(new Ponto(4, 7), new Ponto(7, 4)), 10));
    }

    @Test
    public void testaConstrutor(){
        Roteiro roteiro = new Roteiro(bairros.get(0), bairros.get(1), bairros);   
        assertTrue(bairros.get(0).equals(roteiro.getBairroOrigem()));
        assertTrue(bairros.get(1).equals(roteiro.getBairroDestino()));
    }

    @Test
    public void testaRota(){
        Roteiro roteiro = new Roteiro(bairros.get(0), bairros.get(1), bairros);
        Reta rotaEsp = new Reta(new Ponto(2,2),new Ponto(5,2));
        assertEquals(rotaEsp,roteiro.getRota());
    }

    @Test
    public void testaBairrosPercorridos(){
        Roteiro roteiro = new Roteiro(bairros.get(0), bairros.get(1), bairros);
        Collection<Bairro> esperado = new ArrayList<>();
        esperado.add(bairros.get(0));
        esperado.add(bairros.get(1));
        Collection<Bairro> observado = roteiro.bairrosPercoridos();
        assertEquals(esperado, observado);
    }

    @Test
    public void testaBairrosPercorridos2(){
        Roteiro roteiro = new Roteiro(bairros.get(0), bairros.get(2), bairros);
        Collection<Bairro> esperado = new ArrayList<>();
        esperado.add(bairros.get(0));
        esperado.add(bairros.get(1));
        esperado.add(bairros.get(2));
        Collection<Bairro> observado = roteiro.bairrosPercoridos();
        assertEquals(esperado, observado);
    }

    @Test
    public void testaEquals(){
        Roteiro roteiro = new Roteiro(bairros.get(0), bairros.get(1), bairros);
        Roteiro roteiroIgual = new Roteiro(bairros.get(0), bairros.get(1), bairros);
        Roteiro roteiroDiferente = new Roteiro(bairros.get(0), bairros.get(2), bairros);
        assertTrue(roteiro.equals(roteiroIgual));
        assertFalse(roteiro.equals(roteiroDiferente));
    }
}