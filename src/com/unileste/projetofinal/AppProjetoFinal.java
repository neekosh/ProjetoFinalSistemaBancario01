package com.unileste.projetofinal;
import com.unileste.projetofinal.gui.MainFrame;
public class AppProjetoFinal {
    public static void main(String[] args) {
       java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
    
}
