import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

// our class extends the Frame class to inherit its properties  
// and implements ActionListener interface to override its methods  
public class AlignFoo extends Frame implements ActionListener {
    Label label;
    TextArea textArea;
    Button button;

    // constructor to instantiate
    AlignFoo() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // instantiating and setting the location of components on the frame
        label = new Label("Insert text");
        label.setBounds(50, 50, 300, 30);
        textArea = new TextArea();
        Font fFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        textArea.setFont(fFont);
        textArea.setBounds(20, 100, 300, 300);
        button = new Button("Align");
        button.setBounds(100, 400, 100, 30);

        // adding ActionListener to button
        button.addActionListener(this);

        // adding components to frame
        add(label);
        add(textArea);
        add(button);
        // setting the size, layout and visibility of frame
        setSize(400, 450);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String text = textArea.getText();
        text = alignFoo(text, false);
        StringBuilder output = new StringBuilder();
        String[] lines = text.split("\n");
        ArrayList<Integer> length = new ArrayList<Integer>();
        for (String line : lines) {
            String[] sectors = line.split("(, +)");
            for (int i = 0; i < sectors.length; i++) {
                if(length.size() <= i)
                    length.add(sectors[i].length());
                else if (sectors[i].length() > length.get(i))
                    length.set(i, sectors[i].length());
            }
        }
        for (String line : lines) {
            String[] sectors = line.split("(, +)");
            for (int i = 0; i < sectors.length-1; i++) {
                output.append(sectors[i]).append(", ").append(" ".repeat(length.get(i) - sectors[i].length()));
            }
            output.append(sectors[sectors.length - 1]).append("\n");
        }

        textArea.setText(alignFoo(output.toString(), true));
        label.setText("Text aligned");

    }

    private String alignFoo(String text, boolean closing) {
        String regex = closing ? ")" : "(";
        text = text.replaceAll("( +\\" + regex + ")", regex);

        StringBuilder output = new StringBuilder();
        String[] lines = text.split("\n");

        int length = 0;

        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim();
            if(lines[i].indexOf(regex) > length)
                length = lines[i].indexOf(regex);
        }
        for (String line : lines) {
            output.append(line.replace(regex, " ".repeat(length - line.indexOf(regex)) + regex)).append("\n");
        }
        return output.toString();
    }

    // main method
    public static void main(String[] args) {
        new AlignFoo();
    }
}