package ecochat.interfaces.telas;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ecochat.aplicacoes.telas.JanelaBase;
import ecochat.utilitarios.Utilitaria;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class UIJanelaCadastrar extends JanelaBase {
	private JLabel nome, email, senha;
	private JTextField tnome, temail;
	private JPasswordField psenha;
	private JButton ok, exit;
	// private Image fundo;

	public UIJanelaCadastrar() {

		this.setBounds(500, 500, 251, 350);
		this.setLocationRelativeTo(null);

		nome = new JLabel("Nome:");
		nome.setFont(new Font("Tahoma", Font.BOLD, 13));
		nome.setForeground(Color.WHITE);
		senha = new JLabel("Senha: ");
		senha.setFont(new Font("Tahoma", Font.BOLD, 13));
		senha.setForeground(Color.WHITE);
		temail = new JTextField();
		psenha = new JPasswordField();
		ok = new JButton("OK");
		exit = new JButton("Sair");
		senha.setBounds(20, 200, 50, 20);
		temail.setBounds(70, 160, 150, 20);
		nome.setBounds(20, 120, 50, 20);
		psenha.setBounds(70, 200, 150, 20);
		ok.setBounds(26, 250, 90, 20);
		exit.setBounds(130, 250, 90, 20);

		getContentPane().setLayout(null);

		getContentPane().add(nome);
		getContentPane().add(senha);
		email = new JLabel("Email:");
		email.setFont(new Font("Tahoma", Font.BOLD, 13));
		email.setForeground(Color.WHITE);
		
				email.setBounds(20, 160, 50, 20);
				getContentPane().add(email);
				email.addKeyListener(this);
		tnome = new JTextField();
		tnome.setBounds(70, 120, 150, 20);
		getContentPane().add(tnome);
		tnome.addKeyListener(this);
		getContentPane().add(psenha);
		getContentPane().add(temail);
		getContentPane().add(ok);
		getContentPane().add(exit);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(UIJanelaCadastrar.class.getResource("/ecochat/interfaces/telas/imagens/Logo1.png")));
		label_1.setBounds(-11, 0, 98, 96);
		getContentPane().add(label_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(UIJanelaCadastrar.class.getResource("/ecochat/interfaces/telas/imagens/background4.jpg")));
		label.setBounds(0, 0, 245, 321);
		getContentPane().add(label);
		getContentPane().addKeyListener(this);
		// ImageIcon fundolg = new
		// ImageIcon(Login.class.getResource("/fundo.jpg"));
		// fundo = fundolg.getImage();

		exit.addActionListener(this);
		ok.addActionListener(this);
		senha.addKeyListener(this);
		temail.addKeyListener(this);
		// cadastrar.addActionListener(this);
		this.addWindowListener(this);
		this.addKeyListener(this);
		repaint();
	}

	public void keyPressed(KeyEvent ke) {

		if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
			cadastrarUsuario();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			int b = JOptionPane.showConfirmDialog(null, "Deseja Sair?", null, JOptionPane.YES_NO_OPTION);
			if (b == JOptionPane.YES_OPTION) {
				this.dispose();
			}
		} else if (e.getSource() == ok) {
			cadastrarUsuario();
		}
	}

	private void cadastrarUsuario() {
		String nome = tnome.getText();
		String email = temail.getText();
		String senha = new String(psenha.getPassword());

		if (!(email == null || email.equals("")) && !(senha == null || senha.equals(""))
				&& !(nome == null || nome.equals(""))) {
			Utilitaria.cadastrarUsuario(nome, email, senha);
			UIJanelaLogin.getInstance();
			JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
			this.dispose();
		}
	}
}