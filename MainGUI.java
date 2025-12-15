/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booking_kamar;

/**
 *
 * @author Pongo
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.text.NumberFormat;
import java.util.Locale;

public class MainGUI extends JFrame {
    private HotelManager manager = new HotelManager();
    private JComboBox<Room> cbRooms;
    private JTextField tfName, tfPhone, tfNights, tfCheckIn;
    private JCheckBox chBreakfast, chAirport;
    private DefaultTableModel model;

    public MainGUI() {
        setTitle("HotelBooker - Simple");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
    }

    private void init() {
        JPanel left = new JPanel(new GridLayout(0,1,5,5));
        tfName = new JTextField(); tfPhone = new JTextField();
        cbRooms = new JComboBox<>();
        for (Room r : manager.getRooms()) cbRooms.addItem(r);
        tfCheckIn = new JTextField(LocalDate.now().toString());
        tfNights = new JTextField("1");
        chBreakfast = new JCheckBox("Breakfast");
        chAirport = new JCheckBox("Airport pickup");
        JButton btnAdd = new JButton("Add Booking");
        btnAdd.addActionListener(e -> onAdd());

        left.add(new JLabel("Name:")); left.add(tfName);
        left.add(new JLabel("Phone:")); left.add(tfPhone);
        left.add(new JLabel("Room:")); left.add(cbRooms);
        left.add(new JLabel("Check-in (YYYY-MM-DD):")); left.add(tfCheckIn);
        left.add(new JLabel("Nights:")); left.add(tfNights);
        left.add(chBreakfast); left.add(chAirport);
        left.add(btnAdd);

        String[] cols = {"ID","Name","Room","Check-in","Nights","Total"};
        model = new DefaultTableModel(cols,0);
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);

        JButton btnCancel = new JButton("Cancel Selected");
        btnCancel.addActionListener(e -> {
            int r = table.getSelectedRow();
            if (r >= 0) {
                int id = (int) model.getValueAt(r, 0);
                if (manager.cancelBooking(id)) model.removeRow(r);
            }
        });

        getContentPane().setLayout(new BorderLayout(6,6));
        getContentPane().add(left, BorderLayout.WEST);
        getContentPane().add(sp, BorderLayout.CENTER);
        getContentPane().add(btnCancel, BorderLayout.SOUTH);
    }

    private void onAdd() {
        try {
            String name = tfName.getText().trim();
            String phone = tfPhone.getText().trim();
            Room room = (Room) cbRooms.getSelectedItem();
            LocalDate checkIn = LocalDate.parse(tfCheckIn.getText().trim());
            int nights = Integer.parseInt(tfNights.getText().trim());
            boolean breakfast = chBreakfast.isSelected();
            boolean airport = chAirport.isSelected();

            Booking b = manager.addBooking(name, phone, room, checkIn, nights, breakfast, airport);
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("in","ID"));
            model.addRow(new Object[]{b.getId(), b.getCustomer().getName(), b.getRoom().getName(),
                    b.getCheckIn().toString(), b.getNights(), nf.format(b.getTotal())});

            // clear simple
            tfName.setText(""); tfPhone.setText(""); tfNights.setText("1");
            chBreakfast.setSelected(false); chAirport.setSelected(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}
