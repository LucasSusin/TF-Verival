package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.bcopstein.entidades.Passageiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassageiroTest {
    private Passageiro passageiroTeste;

    @BeforeEach
    public void setup(){
        passageiroTeste = new Passageiro("000.000.000-00", "Mateus ABC", 8, 1);
    }

    @Test
    public void testaNovoPassageiro(){
        Passageiro passageiroNovo = Passageiro.novoPassageiro("000.000.000-00", "Mateus ABC");
        assertEquals(passageiroNovo.getCPF(), passageiroTeste.getCPF());
        assertEquals(passageiroNovo.getNome(), passageiroTeste.getNome());
        assertEquals(passageiroNovo.getPontuacaoAcumulada(), passageiroTeste.getPontuacaoAcumulada());
        assertEquals(passageiroNovo.getQtdadeAvaliacoes(), passageiroTeste.getQtdadeAvaliacoes());
    }

    @Test
    public void testaPassageiroExistente(){
        Passageiro passageiroExistente = Passageiro.passageiroExistente("000.000.000-00", "Mateus ABC",8,1);
        assertEquals(passageiroExistente.getCPF(), passageiroTeste.getCPF());
        assertEquals(passageiroExistente.getNome(), passageiroTeste.getNome());
        assertEquals(passageiroExistente.getPontuacaoAcumulada(), passageiroTeste.getPontuacaoAcumulada());
        assertEquals(passageiroExistente.getQtdadeAvaliacoes(), passageiroTeste.getQtdadeAvaliacoes());
    }

    @Test
    public void testaGetPontuacaoMedia(){
        assertEquals(passageiroTeste.getPontuacaoMedia(), 8);
    }

    @Test
    public void testaAddPontuacao(){
        passageiroTeste.addPontuacao(1);
        Passageiro passageiroExistene = Passageiro.passageiroExistente("000.000.000-00", "Mateus ABC", 9,2);
        assertEquals(passageiroTeste.getCPF(), passageiroExistene.getCPF());
        assertEquals(passageiroTeste.getNome(), passageiroExistene.getNome());
        assertEquals(passageiroTeste.getPontuacaoAcumulada(), passageiroExistene.getPontuacaoAcumulada());
        assertEquals(passageiroTeste.getQtdadeAvaliacoes(), passageiroExistene.getQtdadeAvaliacoes());
    }

    @Test
    public void testaAddPontuacaoInvalida(){  
        try {
            passageiroTeste.addPontuacao(-1);
        } catch (Exception e) {
            assertEquals("Pontucao invalida !", e.getMessage());
        }
    }
}
