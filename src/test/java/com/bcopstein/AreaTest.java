package com.bcopstein;

import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;
import com.bcopstein.entidades.geometria.SituacaoReta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class AreaTest {
    private Area area;

    @BeforeEach
    public void setup(){
         area = new Area(new Ponto(0,2),new Ponto(6,0));
    }

    @ParameterizedTest
    @CsvSource({"1,3,5,1"})
    public void testaConstrutor(int x1,int y1,int x2,int y2){
        Ponto superior = new Ponto(x1, y1);
        Ponto inferior = new Ponto(x2, y2);
        Area area2 = new Area(superior, inferior);
        assertTrue(area2.getPSupEsq().equals(superior));
        assertTrue(area2.getPInfDir().equals(inferior));
    }

    @ParameterizedTest
    @CsvSource({"2,5"})
    public void testaPontoCentral(int centralX, int centralY){
        Ponto central = new Ponto(centralX, centralY);
        assertFalse(area.pontoCentral().equals(central));
    }

    @ParameterizedTest
    @CsvSource({"2,4,1",
                "1,-1,2",
                "6,2,0",
                "-3,1,8",
                "2,2,0"})
    public void testaCodificaPonto(int pontoX, int pontoY, int byteCorretoint){
        Ponto ponto = new Ponto(pontoX, pontoY);

        byte number = area.codificaPonto(ponto);
        byte byteCorreto = (byte)byteCorretoint;
        assertEquals(number, byteCorreto);
    }

    @ParameterizedTest
    @CsvSource({"2,2,4,1,TODA_DENTRO",
                "7,1,8,2,TODA_FORA",
                "3,3,5,4,TODA_FORA",
                "-2,1,-1,1,TODA_FORA",
                "3,-2,5,-3,TODA_FORA",
                "-3,-5,-2,-2,TODA_FORA",
                "-5,5,-2,3,TODA_FORA",
                "7,3,9,6,TODA_FORA",
                "7,-1,9,-4,TODA_FORA",
                "3,1,8,3,INTERSECTA"})
    public void testaClassifica(int x1,int y1,int x2,int y2,String classificacao){
        Reta reta = new Reta(new Ponto(x1,y1), new Ponto(x2,y2));
        SituacaoReta sitEsp = switch(classificacao){
            case "TODA_DENTRO" -> SituacaoReta.TODA_DENTRO;
            case "TODA_FORA" -> SituacaoReta.TODA_FORA;
            case "INTERSECTA" -> SituacaoReta.INTERSECTA;
            default -> SituacaoReta.TODA_DENTRO;
        };
        SituacaoReta sitObs = area.classifica(reta);
        assertEquals(sitEsp, sitObs);
    }

    @ParameterizedTest
    @CsvSource({"0,2,6,0,T",
                "0,3,6,0,F"})
    public void testaEquals(int x1,int y1,int x2,int y2,String esperado){
        Ponto superior = new Ponto(x1, y1);
        Ponto inferior = new Ponto(x2, y2);
        Area area2 = new Area(superior, inferior);
        
        boolean sitEsp = switch(esperado){
            case "T" -> true;
            case "F" -> false;
            default -> false;
        };
        boolean sitObs = area.equals(area2);
        assertEquals(sitEsp, sitObs);
    }
}