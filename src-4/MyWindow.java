import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame{
    public MyWindow() {
        setTitle("Наш чат");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(900, 400, 400, 300);
        JButton jbutton1 = new JButton("Ввод");
        setLayout(new BorderLayout());

        JPanel jpanel1 = new JPanel();
        jpanel1.setBackground(new Color(-30));
        add(jpanel1, BorderLayout.CENTER);
        JPanel jpanel2 = new JPanel();
        jpanel2.setBackground(new Color(100));
        add(jpanel2, BorderLayout.SOUTH);

        jpanel1.setLayout(new BorderLayout());
        JTextArea jTextArea1 = new JTextArea();
        JScrollPane jScrollPane1 = new JScrollPane(jTextArea1);
        jpanel1.add(jScrollPane1);


        jpanel2.setLayout(new BorderLayout());
        JTextField jTextArea2 = new JTextField();
        jpanel2.add(jTextArea2);
        jpanel2.add(jbutton1, BorderLayout.SOUTH);



        jbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea1.setText(jTextArea1.getText() + "\n" + jTextArea2.getText() );
                jTextArea2.setText(null);
            }
        });
        jTextArea2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea1.setText(jTextArea1.getText() + "\n" + jTextArea2.getText());
                jTextArea2.setText(null);
            }
        });

        setVisible(true);
    }

}
