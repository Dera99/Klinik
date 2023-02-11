package app.menu;


import app.services.UserSession;
import app.swing.MenuButton;
import app.themes.SystemTheme;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

public class Menu extends javax.swing.JPanel {

    private MenuButton selectedMenu;
    private MenuButton unSelectedMenu;
    private EventMenu event;
    public int getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(int selectedLocation) {
        this.selectedLocation = selectedLocation;
        repaint();
    }

    public void addEventMenu(EventMenu event) {
        this.events.add(event);
    }

    private int selectedIndex = 0;
    private Animator animator;
    private TimingTarget target;
    private int selectedLocation = 151;
    private int targetLocation;
    private List<EventMenu> events = new ArrayList<>();

    public Menu() {
        initComponents();
        txtNama.setText(UserSession.getNamaMedis());
        setOpaque(false);
        setBackground(Color.WHITE);
        
        menu.setLayout(new MigLayout("fillx, wrap, inset 0", "[fill]", "[fill, 36!]0[fill, 36!]"));
        initMenu();
    }

    public void initMenu(EventMenu event) {
       this.event = event;
       String level = UserSession.getLevel();
       if(level.equals("Admin")){
        addMenu("Beranda", null, 0);
        addMenu("Pendaftaran Pasien", null, 1);
        //addMenu("Pemeriksaan", null, 2);
        addMenu("Jadwal Pelayanan", null, 3);
        addMenu("Data Obat", null, 4);
        addMenu("Daftar Periksa", null,5);
        addMenu("Tenaga Medis", null,6);
        addMenu("Laporan", null,7);
        addMenu("Pengaturan", null,8);
        addMenu("Logout",null,9);
       }else{
        addMenu("Beranda", null, 0);
        //addMenu("Pendaftaran Pasien", null, 1);
        addMenu("Pemeriksaan", null, 2);
        //addMenu("Jadwal Pelayanan", null, 3);
        //addMenu("Data Obat", null, 4);
        addMenu("Daftar Periksa", null,5);
        //addMenu("Tenaga Medis", null,6);
        //addMenu("Laporan", null,7);
        addMenu("Pengaturan", null,8);
        addMenu("Logout",null,9);
       }
    }
    private void initMenu() {
        initMenu(event);
        menu.repaint();
        menu.revalidate();
        setSelectedMenu(0);
        animator = new Animator(300);
        animator.addTarget(new TimingTargetAdapter() {
            @Override
            public void begin() {
                clearSelected();
            }

            @Override
            public void end() {
                setSelectedMenu(selectedIndex);
                runEvent();
            }
        });
        animator.setDeceleration(.5f);
        animator.setAcceleration(.5f);
        animator.setResolution(0);
    }

    private void addMenu(String menuName, String icon, int index) {
        MenuButton m = new MenuButton(index);
        m.setIcoName(icon);
        //m.setIcon(new ImageIcon(getClass().getResource("/app/icon/" + icon + ".png")));
        m.setFont(m.getFont().deriveFont(Font.BOLD, 12));
        m.setForeground(new Color(127, 127, 127));
        m.setHorizontalAlignment(JButton.LEFT);
        m.setText("  " + menuName);
        m.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (m.getIndex() != selectedIndex) {
                    if (animator.isRunning()) {
                        animator.stop();
                    }
                    int y = m.getY() + menu.getY();
                    targetLocation = y;
                    selectedIndex = index;
                    animator.removeTarget(target);
                    target = new PropertySetter(Menu.this, "selectedLocation", selectedLocation, targetLocation);
                    animator.addTarget(target);
                    animator.start();
                }
            }
        });
        menu.add(m);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int y = selectedLocation;
        //g2.setColor(getBackground());
        g2.setColor(SystemTheme.mainColor);
        g2.fill(createShape(y));
        g2.dispose();
        super.paintComponent(grphcs);
    }

    private Shape createShape(int y) {
        int width = getWidth() - 12;
        int r = 20;
        Area area = new Area(new RoundRectangle2D.Float(6, y, width, 35, r, r));
        area.add(new Area(new RoundRectangle2D.Float(width - r + 6, y, r, r, 5, 5)));
        area.add(new Area(new RoundRectangle2D.Float(6, y + 35 - r, r, r, 5, 5)));
        return area;
    }

    private void clearSelected() {
        for (Component com : menu.getComponents()) {
            if (com instanceof MenuButton) {
                MenuButton c = (MenuButton) com;
                c.setForeground(new Color(127, 127, 127));
                c.setEffectColor(new Color(173, 173, 173));
//                if (!c.getIcoName().contains("_s")) {
//                    c.setIcon(new ImageIcon(getClass().getResource("/app/icon/" + c.getIcoName() + ".png")));
//                }
            }
        }
    }

    public void setSelectedMenu(int index) {
        for (Component com : menu.getComponents()) {
            MenuButton btn = (MenuButton) com;
            if(index == btn.getIndex()){
                btn.setForeground(Color.WHITE);
                btn.setEffectColor(Color.WHITE);
                break;
            }
        }
    }

    private void runEvent() {
        for (EventMenu event : events) {
            event.selectedMenu(selectedIndex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPanel();
        txtBrand = new javax.swing.JLabel();
        txtNama = new javax.swing.JLabel();
        imageAvatar1 = new app.themes.ImageAvatar();

        menu.setOpaque(false);

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
        );

        txtBrand.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        txtBrand.setForeground(new java.awt.Color(117, 117, 117));
        txtBrand.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtBrand.setText("KLINIK dr. FAYRUS");

        txtNama.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        txtNama.setForeground(new java.awt.Color(154, 154, 154));
        txtNama.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNama.setText("Admin");

        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/app/report/logo1.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtNama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtBrand, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(txtBrand)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.themes.ImageAvatar imageAvatar1;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel txtBrand;
    private javax.swing.JLabel txtNama;
    // End of variables declaration//GEN-END:variables
}
