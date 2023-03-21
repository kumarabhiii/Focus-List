package com.kumar.abhiii.todolist;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class ToDoList implements Serializable

{
private ArrayList<String> items;
private JFrame frame;
private JList<String> list;
private DefaultListModel<String> listModel;
private JButton addButton, removeButton;
private JButton clearButton;
private JTextField addItemField;
public ToDoList() 
{
// Load items from file or create new list if file doesn't exist
try 
{
FileInputStream fileInputStream = new FileInputStream("todo.dat");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
items = (ArrayList<String>) objectInputStream.readObject();
objectInputStream.close();
fileInputStream.close();
} catch (IOException | ClassNotFoundException e) 
{
items = new ArrayList<>();
}
// Create GUI components
frame = new JFrame("Focus List");
listModel = new DefaultListModel<>();
for (String item : items) 
{
listModel.addElement(item);
}
list = new JList<>(listModel);
list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
JScrollPane listScrollPane = new JScrollPane(list);
listScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
listScrollPane.setPreferredSize(new Dimension(400, 400));
addButton = new JButton("Add");
addButton.setFont(new Font("Arial", Font.BOLD, 14));
addButton.setBackground(new Color(68, 138, 255));
addButton.setForeground(Color.WHITE);
removeButton = new JButton("Remove");
removeButton.setFont(new Font("Arial", Font.BOLD, 14));
removeButton.setBackground(new Color(255, 68, 68));
removeButton.setForeground(Color.WHITE);
addItemField = new JTextField(20);
addItemField.setFont(new Font("Arial", Font.PLAIN, 14));
addItemField.setBorder(BorderFactory.createCompoundBorder(
BorderFactory.createLineBorder(Color.GRAY),
BorderFactory.createEmptyBorder(5, 5, 5, 5)
));

//clear all feature
clearButton = new JButton("Clear All");
clearButton.setFont(new Font("Arial", Font.BOLD, 14));
clearButton.setBackground(new Color(255, 255, 0));
clearButton.setForeground(Color.BLACK);

// Add listeners to buttons and text field
addButton.addActionListener(new ActionListener() 
{
public void actionPerformed(ActionEvent e) 
{
String item = addItemField.getText();
if (!item.isEmpty()) 
{
//its for ui list
listModel.addElement(item);
//its for file list
items.add(item);
addItemField.setText("");
}
}
});
removeButton.addActionListener(new ActionListener() 
{
public void actionPerformed(ActionEvent e) 
{
int index = list.getSelectedIndex();
if (index != -1) 
{
listModel.remove(index);
items.remove(index);
}
}
});

clearButton.addActionListener(new ActionListener() 
{
public void actionPerformed(ActionEvent e) 
{
listModel.clear();
items.clear();
}
});


addItemField.addActionListener(new ActionListener() 
{
public void actionPerformed(ActionEvent e) 
{
addButton.doClick();
}
});
// Arrange components on frame
JPanel buttonPanel = new JPanel();
buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
buttonPanel.add(Box.createHorizontalGlue());
buttonPanel.add(addItemField);
buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
buttonPanel.add(addButton);
buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
buttonPanel.add(removeButton);
buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
buttonPanel.add(clearButton);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.add(listScrollPane, BorderLayout.CENTER);
frame.add(buttonPanel, BorderLayout.PAGE_END);
frame.getRootPane().setDefaultButton(addButton);
frame.pack();
frame.setLocationRelativeTo(null);
frame.setVisible(true);

} //constructor ends here

public void save() 
{
// Save items to file
try 
{
FileOutputStream fileOutputStream = new FileOutputStream("todo.dat");
ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
objectOutputStream.writeObject(items);
objectOutputStream.close();
fileOutputStream.close();
} catch (IOException e) 
{
e.printStackTrace();
}
}


}