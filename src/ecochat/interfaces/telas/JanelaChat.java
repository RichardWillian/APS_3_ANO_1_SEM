package ecochat.interfaces.telas;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ContainerListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ecochat.aplicacoes.cliente.ClienteUm;
import ecochat.aplicacoes.telas.JanelaBase;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.TabSet;

@SuppressWarnings({ "serial", "unused" })
public class JanelaChat extends JanelaBase {

	private JFrame janelaChat;
	private static JanelaChat instancia;
	private JLabel lblEnviarArquivo;
	private JLabel lblEnviarMensagem;
	private JTextArea textAreaCampoEscritaChat;
	private JScrollPane scrollBarCampoEscritaChat;

	private JScrollPane scrollBarVisorChat;
	private JTextPane textPaneVisorChat;
	private StyledDocument conteudoVisor;
	private SimpleAttributeSet ladoDireito;
	private SimpleAttributeSet ladoEsquerdo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaChat.getInstance().janelaChat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaChat() {
		initialize();
	}

	private void initialize() {

		janelaChat = new JFrame();
		janelaChat.setResizable(false);
		janelaChat.setAlwaysOnTop(true);
		janelaChat.setAutoRequestFocus(false);
		janelaChat.setBounds(100, 100, 350, 472);
		janelaChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janelaChat.getContentPane().setLayout(null);

		lblEnviarArquivo = new JLabel(new ImageIcon(this.getClass().getResource("imagens\\anexo_icon_5.png")));
		lblEnviarArquivo.setBounds(10, 396, 45, 40);
		janelaChat.getContentPane().add(lblEnviarArquivo);

		lblEnviarMensagem = new JLabel(new ImageIcon(this.getClass().getResource("imagens\\enviar_icon_1.png")));
		lblEnviarMensagem.setBounds(284, 396, 60, 40);
		janelaChat.getContentPane().add(lblEnviarMensagem);

		textAreaCampoEscritaChat = new JTextArea();
		textAreaCampoEscritaChat.setToolTipText("");
		textAreaCampoEscritaChat.setWrapStyleWord(true);
		textAreaCampoEscritaChat.setLineWrap(true);
		textAreaCampoEscritaChat.setBounds(65, 396, 223, 40);
		textAreaCampoEscritaChat.addKeyListener(this);

		scrollBarCampoEscritaChat = new JScrollPane(textAreaCampoEscritaChat);
		scrollBarCampoEscritaChat.setSize(209, 36);
		scrollBarCampoEscritaChat.setLocation(65, 396);
		scrollBarCampoEscritaChat.setVisible(true);
		scrollBarCampoEscritaChat.setEnabled(true);
		janelaChat.getContentPane().add(scrollBarCampoEscritaChat);

		textPaneVisorChat = new JTextPane();
		textPaneVisorChat.setBounds(0, 0, 344, 384);
		textPaneVisorChat.setMargin(new Insets(20, 15, 5, 15));
		textPaneVisorChat.setContentType("text/html");
		
		scrollBarVisorChat = new JScrollPane(textPaneVisorChat);
		scrollBarVisorChat.setVisible(true);
		scrollBarVisorChat.setEnabled(true);
		scrollBarVisorChat.setSize(344, 384);
		scrollBarVisorChat.setLocation(0, 0);
		scrollBarVisorChat.setViewportView(textPaneVisorChat);
		
		conteudoVisor = textPaneVisorChat.getStyledDocument();

		ladoEsquerdo = new SimpleAttributeSet();
		StyleConstants.setAlignment(ladoEsquerdo, StyleConstants.ALIGN_LEFT);
		StyleConstants.setForeground(ladoEsquerdo, Color.RED);

		ladoDireito = new SimpleAttributeSet();
		StyleConstants.setAlignment(ladoDireito, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setForeground(ladoDireito, Color.BLUE);

		janelaChat.getContentPane().add(scrollBarVisorChat);

		janelaChat.setVisible(true);
	}

	public static JanelaChat getInstance() {

		if (instancia == null)
			return instancia = new JanelaChat();

		return instancia;
	}

	public void adicionarMensagemDireita(String mensagem) {
		
		try {
			conteudoVisor.insertString(conteudoVisor.getLength(), mensagem, ladoDireito);
			conteudoVisor.setParagraphAttributes(conteudoVisor.getLength(), 10, ladoDireito, false);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void adicionarMensagemEsquerda(String mensagem) {
		try {
			conteudoVisor.insertString(conteudoVisor.getLength(), mensagem, ladoEsquerdo);
			conteudoVisor.setParagraphAttributes(conteudoVisor.getLength(), 1, ladoEsquerdo, false);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
			ClienteUm.getInstance();
			adicionarMensagemDireita(textAreaCampoEscritaChat.getText());
			ClienteUm.escreverMensagemAoServidor(textAreaCampoEscritaChat.getText());
			textAreaCampoEscritaChat.setText(null);
		}
	}
}
