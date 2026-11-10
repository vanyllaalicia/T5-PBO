import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
     
public class KalkulatorKaloriMakanan extends JFrame
        implements ActionListener, ItemListener {

    private JComboBox<String> cmbMakanan;
    private JSpinner spnPorsi, spnTarget;
    private JLabel lblInfo, lblTotal, lblJumlah, lblTarget;
    private JTable tabel;
    private DefaultTableModel model;
    private JButton btnTambah, btnHapus, btnReset;

    private final DecimalFormat df = new DecimalFormat("#,##0.##");
    private final String[] makanan = {
        "Nasi putih", "Ayam Geprek", "Ayam goreng",
        "Telur rebus", "Tempe goreng", "Tahu goreng",
        "Pisang", "Roti tawar", "Mi instan", "Soto ayam",
        "Gado-gado", "Kentang rebus", "Ikan bakar",
        "Cilok", "Jus Buah", "Sandwich", "Burger",
        "Pizza", "Nasi Padang", "Sate", "Ayam Bakar",
        "Mie Ayam", "Bakso", "Gorengan", "Es teh"
    };

    private final int[] kalori = {
        175, 450, 260, 78, 120, 90, 105, 75, 380,
        250, 300, 130, 220, 300, 180, 300, 500,
        350, 650, 350, 400, 400, 350, 200, 150
    };

    public KalkulatorKaloriMakanan() {
        setTitle("Calorie Counter");
        setSize(780, 570);
        setMinimumSize(new Dimension(700, 520));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel judul = new JLabel("CALORIE COUNTER", JLabel.CENTER);
        judul.setFont(new Font("Arial", Font.BOLD, 24));
        add(judul, BorderLayout.NORTH);

        JPanel input = new JPanel(new GridLayout(3, 2, 10, 10));
        input.setBorder(BorderFactory.createTitledBorder("Tambah Makanan"));

        cmbMakanan = new JComboBox<>(makanan);
        spnPorsi = new JSpinner(
            new SpinnerNumberModel(1.0, 0.5, 50.0, 0.5));
        lblInfo = new JLabel();

        input.add(new JLabel("Pilih makanan:"));
        input.add(cmbMakanan);
        input.add(new JLabel("Jumlah porsi:"));
        input.add(spnPorsi);
        input.add(new JLabel("Kalori per porsi:"));
        input.add(lblInfo);

        btnTambah = new JButton("Tambah ke Daftar");

        JPanel atas = new JPanel(new BorderLayout());
        atas.add(input, BorderLayout.CENTER);
        atas.add(btnTambah, BorderLayout.SOUTH);


        model = new DefaultTableModel(
            new String[]{"Makanan", "Porsi", "Kalori/Porsi", "Total Kalori"}, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tabel = new JTable(model);
        tabel.setRowHeight(24);

        JPanel tengah = new JPanel(new BorderLayout(10, 10));
        tengah.add(atas, BorderLayout.NORTH);
        tengah.add(new JScrollPane(tabel), BorderLayout.CENTER);

        JPanel ringkasan = new JPanel(new GridLayout(4, 1, 5, 5));
        ringkasan.setBorder(BorderFactory.createTitledBorder("Ringkasan Kalori"));

        lblTotal = new JLabel("Total kalori: 0 kkal");
        lblJumlah = new JLabel("Jumlah item: 0");
        lblTarget = new JLabel();

        spnTarget = new JSpinner(
            new SpinnerNumberModel(2000, 500, 10000, 100));

        ringkasan.add(lblTotal);
        ringkasan.add(lblJumlah);
        ringkasan.add(new JLabel("Target harian (kkal):"));
        ringkasan.add(spnTarget);

        btnHapus = new JButton("Hapus Terpilih");
        btnReset = new JButton("Kosongkan Daftar");

        JPanel tombol = new JPanel();
        tombol.add(btnHapus);
        tombol.add(btnReset);

        JPanel bawah = new JPanel(new BorderLayout());
        bawah.add(ringkasan, BorderLayout.CENTER);
        bawah.add(lblTarget, BorderLayout.NORTH);
        bawah.add(tombol, BorderLayout.SOUTH);

        add(tengah, BorderLayout.CENTER);
        add(bawah, BorderLayout.SOUTH);

        btnTambah.addActionListener(this);
        btnHapus.addActionListener(this);
        btnReset.addActionListener(this);
        cmbMakanan.addItemListener(this);
        spnTarget.addChangeListener(e -> perbaruiRingkasan());

        perbaruiInfo();
        perbaruiRingkasan();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTambah) {
            tambahMakanan();
        } else if (e.getSource() == btnHapus) {
            hapusMakanan();
        } else if (e.getSource() == btnReset) {
            model.setRowCount(0);
            perbaruiRingkasan();
        }
    }

    private void tambahMakanan() {
        int i = cmbMakanan.getSelectedIndex();
        double porsi = ((Number) spnPorsi.getValue()).doubleValue();
        double total = kalori[i] * porsi;

        model.addRow(new Object[]{
            makanan[i], df.format(porsi), kalori[i], total
        });

        perbaruiRingkasan();
    }

    private void hapusMakanan() {
        int baris = tabel.getSelectedRow();

        if (baris == -1) {
            JOptionPane.showMessageDialog(this,
                "Pilih makanan yang ingin dihapus!");
            return;
        }

        model.removeRow(baris);
        perbaruiRingkasan();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            perbaruiInfo();
        }
    }

    private void perbaruiInfo() {
        int i = cmbMakanan.getSelectedIndex();
        lblInfo.setText(kalori[i] + " kkal (estimasi)");
    }

    private void perbaruiRingkasan() {
        double total = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            total += ((Number) model.getValueAt(i, 3)).doubleValue();
        }

        double target = ((Number) spnTarget.getValue()).doubleValue();
        double persen = total / target * 100;

        lblTotal.setText("Total kalori: " + df.format(total) + " kkal");
        lblJumlah.setText("Jumlah item: " + model.getRowCount());

        lblTarget.setText(String.format(
            "Target tercapai: %.1f%% (%s dari %s kkal)",
            persen, df.format(total), df.format(target)));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new KalkulatorKaloriMakanan().setVisible(true);
        });
    }
}
