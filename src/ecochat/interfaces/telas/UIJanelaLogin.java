package ecochat.interfaces.telas;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ecochat.aplicacoes.servidor.ServidorChatAplicacao;
import ecochat.aplicacoes.telas.JanelaBase;
import ecochat.utilitarios.Utilitaria;

@SuppressWarnings("serial")
public class UIJanelaLogin extends JanelaBase {

	private JLabel login, senha, image;
	private JTextField tlg;
	private JPasswordField psenha;
	private JButton ok, exit, cadastrar;
	@SuppressWarnings("unused")
	private Image fundo;
	@SuppressWarnings("unused")
	private String log;
	
	private static UIJanelaLogin instancia;

	public UIJanelaLogin() {

		// ImageIcon fundolg = new
		// ImageIcon(Login.class.getResource("/fundo.jpg"));
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("EcoLx Login");
		this.setBounds(300, 300, 350, 200);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		image = new JLabel(new ImageIcon("/fundo.jpg"));
		login = new JLabel("Login:");
		senha = new JLabel("Senha: ");
		tlg = new JTextField();
		psenha = new JPasswordField();
		ok = new JButton("OK");
		exit = new JButton("Sair");
		cadastrar = new JButton("Cadastrar");

		// image.setBounds(200, 50, 150, 150);
		login.setBounds(20, 50, 50, 20);
		senha.setBounds(20, 75, 50, 20);
		tlg.setBounds(70, 50, 150, 20);
		psenha.setBounds(70, 75, 150, 20);
		ok.setBounds(20, 100, 90, 20);
		exit.setBounds(120, 100, 90, 20);
		cadastrar.setBounds(220, 100, 100, 20);

		this.setLayout(null);

		this.add(login);
		this.add(senha);
		this.add(psenha);
		this.add(tlg);
		this.add(ok);
		this.add(exit);
		this.add(cadastrar);
		this.add(image);

		ok.addActionListener(this);
		exit.addActionListener(this);
		cadastrar.addActionListener(this);
		this.addWindowListener(this);
		repaint();
	}

	// public void paintComponent(Graphics a) {
	// Graphics2D graficos = (Graphics2D) a;
	// graficos.drawImage(fundo, 0, 0, 200, 200, null);
	// //Image img = fundo.getImage();
	// //graficos.dispose();
	//
	// }

	public void actionPerformed(ActionEvent a) {
		
		if (a.getSource() == ok) {
			
			String email = tlg.getText();
			char[] caracteresSenha = psenha.getPassword();
			String senha = new String(caracteresSenha);
			
			if(Utilitaria.verificarAutenticacaoUsuario(email, senha)){
				// TODO CHAMAR A TELA DO VITOR
				this.dispose();
				ServidorChatAplicacao.getInstance().entrarChat();
			}
		}
		
		if (a.getSource() == exit) {
			int b = JOptionPane.showConfirmDialog(null, "Deseja Sair?", null, JOptionPane.YES_NO_OPTION);
			if (b == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}

		if (a.getSource() == cadastrar) {
			UIJanelaCadastrar cadastro = new UIJanelaCadastrar();
			cadastro.setVisible(true);
		}
	}

	public void windowClosing(WindowEvent e) {
		int b = JOptionPane.showConfirmDialog(null, "Deseja Sair?", null, JOptionPane.YES_NO_OPTION);
		if (b == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public static UIJanelaLogin getInstance() {
		
		if(instancia == null)
			return instancia = new UIJanelaLogin();
		
		return instancia;
	}
}
