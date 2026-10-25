import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class KalkulatorKaloriMakanan extends JFrame
        implements ActionListener, ItemListener {

    private JComboBox<String> cmbMakanan;
    private JSpinner spnPorsi;
    private JLabel lblInfoKalori;
    private JLabel lblTotalKalori;
    private JLabel lblJumlahItem;
    private JLabel lblTarget;
    private JTable tabelMakanan;
    private DefaultTableModel modelTabel;
    private JButton btnTambah;
    private JButton btnHapus;
    private JButton btnReset;
    private JSpinner spnTarget;

    private final DecimalFormat angka = new DecimalFormat("#,##0.##");

    // Data contoh: kalori perkiraan per satu porsi.
    private final String[] namaMakanan = {
        "Nasi putih","Ayam Geprek","Ayam goreng", "Telur rebus", "Tempe goreng",
        "Tahu goreng", "Pisang", "Roti tawar", "Mi instan", "Soto ayam", 
        "Gado-gado", "Kentang rebus", "Ikan bakar", "Cilok (10k)", "Jus Buah", 
        "Sandwich", "Burger", "Pizza", "Nasi Padang", "Sate", 
        "Ayam Bakar", "Mie Ayam", "Bakso", "Gorengan", "Es teh"
    };

    private final int[] kaloriMakanan = {
        175, 450, 260, 78, 120,
        90, 105, 75, 380, 250,
        300, 130, 220, 300, 180, 
        300, 500, 350, 650, 350, 
        400, 400, 350, 200, 150
    };

    public KalkulatorKaloriMakanan() {
        setTitle("Calorie Counter - Kalkulator Kalori Makanan");
        setSize(780, 570);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new java.awt.Dimension(700, 520));

        // Top-level container.
        setLayout(new BorderLayout(12, 12));

        JLabel judul = new JLabel(
                "CALORIE COUNTER", SwingConstants.CENTER);
        judul.setFont(new Font("Arial", Font.BOLD, 24));
        judul.setBorder(BorderFactory.createEmptyBorder(18, 10, 5, 10));
        add(judul, BorderLayout.NORTH);

        // Intermediate component untuk mengelompokkan form dan tabel.
        JPanel panelUtama = new JPanel(new BorderLayout(12, 12));
        panelUtama.setBorder(BorderFactory.createEmptyBorder(0, 18, 18, 18));

        JPanel panelInput = new JPanel(new GridLayout(3, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createTitledBorder("Tambah Makanan"));

        cmbMakanan = new JComboBox<>(namaMakanan);
        spnPorsi = new JSpinner(new SpinnerNumberModel(1.0, 0.5, 50.0, 0.5));
        lblInfoKalori = new JLabel();

        panelInput.add(new JLabel("Pilih makanan:"));
        panelInput.add(cmbMakanan);
        panelInput.add(new JLabel("Jumlah porsi:"));
        panelInput.add(spnPorsi);
        panelInput.add(new JLabel("Kalori per porsi:"));
        panelInput.add(lblInfoKalori);

        JPanel panelAtas = new JPanel(new BorderLayout(8, 8));
        panelAtas.add(panelInput, BorderLayout.CENTER);

        JPanel panelTombolInput = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnTambah = new JButton("Tambah ke Daftar");
        panelTombolInput.add(btnTambah);
        panelAtas.add(panelTombolInput, BorderLayout.SOUTH);

        modelTabel = new DefaultTableModel(
                new Object[]{"Makanan", "Porsi", "Kalori per Porsi", "Total Kalori"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelMakanan = new JTable(modelTabel);
        tabelMakanan.setRowHeight(24);
        JScrollPane scrollTabel = new JScrollPane(tabelMakanan);
        scrollTabel.setBorder(BorderFactory.createTitledBorder("Daftar Konsumsi"));

        JPanel panelTengah = new JPanel(new BorderLayout(8, 8));
        panelTengah.add(panelAtas, BorderLayout.NORTH);
        panelTengah.add(scrollTabel, BorderLayout.CENTER);

        JPanel panelRingkasan = new JPanel(new GridLayout(3, 1, 5, 5));
        panelRingkasan.setBorder(BorderFactory.createTitledBorder("Ringkasan Kalori"));

        lblTotalKalori = new JLabel("Total kalori: 0 kkal");
        lblTotalKalori.setFont(new Font("Arial", Font.BOLD, 16));
        lblJumlahItem = new JLabel("Jumlah item: 0");
        lblTarget = new JLabel();

        JPanel panelTarget = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelTarget.add(new JLabel("Target harian (kkal):"));
        spnTarget = new JSpinner(new SpinnerNumberModel(2000, 500, 10000, 100));

        panelRingkasan.add(lblTotalKalori);
        panelRingkasan.add(lblJumlahItem);
        panelRingkasan.add(panelTarget);
        panelTarget.add(spnTarget);

        JPanel panelBawah = new JPanel(new BorderLayout(5, 5));
        panelBawah.add(panelRingkasan, BorderLayout.CENTER);
        panelBawah.add(lblTarget, BorderLayout.SOUTH);

        JPanel panelAksi = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnHapus = new JButton("Hapus Makanan Terpilih");
        btnReset = new JButton("Kosongkan Daftar");
        panelAksi.add(btnHapus);
        panelAksi.add(btnReset);

        JPanel panelFooter = new JPanel(new BorderLayout(5, 5));
        panelFooter.add(panelBawah, BorderLayout.CENTER);
        panelFooter.add(panelAksi, BorderLayout.SOUTH);

        panelUtama.add(panelTengah, BorderLayout.CENTER);
        panelUtama.add(panelFooter, BorderLayout.SOUTH);
        add(panelUtama, BorderLayout.CENTER);

        // Event handling dengan dua interface Java.
        btnTambah.addActionListener(this);
        btnHapus.addActionListener(this);
        btnReset.addActionListener(this);
        cmbMakanan.addItemListener(this);
        spnTarget.addChangeListener(e -> perbaruiRingkasan());

        perbaruiInfoKalori();
        perbaruiRingkasan();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object sumber = e.getSource();

        if (sumber == btnTambah) {
            tambahMakanan();
        } else if (sumber == btnHapus) {
            hapusMakananTerpilih();
        } else if (sumber == btnReset) {
            kosongkanDaftar();
        }
    }

    private void tambahMakanan() {
        int indeks = cmbMakanan.getSelectedIndex();
        String makanan = namaMakanan[indeks];
        double porsi = ((Number) spnPorsi.getValue()).doubleValue();
        int kaloriPerPorsi = kaloriMakanan[indeks];
        double totalKalori = kaloriPerPorsi * porsi;

        modelTabel.addRow(new Object[]{
            makanan, angka.format(porsi), kaloriPerPorsi, angka.format(totalKalori)
        });

        perbaruiRingkasan();
    }

    private void hapusMakananTerpilih() {
        int baris = tabelMakanan.getSelectedRow();

        if (baris == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pilih satu baris makanan dari tabel terlebih dahulu.",
                    "Belum Ada Pilihan",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        modelTabel.removeRow(baris);
        perbaruiRingkasan();
    }

    private void kosongkanDaftar() {
        modelTabel.setRowCount(0);
        perbaruiRingkasan();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cmbMakanan
                && e.getStateChange() == ItemEvent.SELECTED) {
            perbaruiInfoKalori();
        }
    }

    private void perbaruiInfoKalori() {
        int indeks = cmbMakanan.getSelectedIndex();
        lblInfoKalori.setText(kaloriMakanan[indeks] + " kkal per porsi (estimasi)");
    }

    private void perbaruiRingkasan() {
        double total = 0;
        for (int i = 0; i < modelTabel.getRowCount(); i++) {
            String nilai = modelTabel.getValueAt(i, 3).toString().replace(",", "");
            total += Double.parseDouble(nilai);
        }

        int jumlahItem = modelTabel.getRowCount();
        double target = ((Number) spnTarget.getValue()).doubleValue();
        double persentase = (total / target) * 100.0;

        lblTotalKalori.setText("Total kalori: " + angka.format(total) + " kkal");
        lblJumlahItem.setText("Jumlah item makanan: " + jumlahItem);
        lblTarget.setText(String.format(
                "<html>Target tercapai: %.1f%% (%s dari %s kkal)</html>",
                persentase, angka.format(total), angka.format(target)));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KalkulatorKaloriMakanan aplikasi = new KalkulatorKaloriMakanan();
            aplikasi.setVisible(true);
        });
    }
}
