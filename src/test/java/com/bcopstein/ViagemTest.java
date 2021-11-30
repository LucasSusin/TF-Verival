package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.Viagem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ViagemTest {
    private LocalDateTime tempo;
    private Roteiro roteiro;
    private Passageiro passageiro;

    @BeforeEach
    public void setup(){
        tempo = LocalDateTime.now();
        roteiro = mock(Roteiro.class);
        passageiro = mock(Passageiro.class);
    }

    @Test
    public void testaConstrutor(){
        Viagem v = new Viagem(1, tempo, roteiro, passageiro, 50);
        assertTrue(tempo.equals(v.getDataHora()));
        assertTrue(roteiro.equals(v.getRoteiro()));
        assertTrue(passageiro.equals(v.getPassageiro()));
        assertTrue(50 == v.getValorCobrado());
        assertTrue(1 == v.getId());
        
    }
}
