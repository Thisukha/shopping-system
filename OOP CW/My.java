import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class My extends JFrame implements ActionListener {
    private JButton My_Button;
    private JComboBox<String> Combo_Box;
    private JTable Product_Table;
    private DefaultTableModel Table_Model;
    private WestminsterShoppingManager Shopping_Manager;
    private JLabel Select_Category_Label;
    private JButton Shopping_Cart_Button;

    private JTable Shopping_Cart_Table;

    private JLabel Select_Products_Label;
    private JLabel Product_ID_Label;
    private JLabel Category_Label;
    private JLabel Name_Label;
    private JLabel Size_Label;
    private JLabel Color_Label;
    private JLabel Items_Available_Label;
    private JLabel Brand_Label;
    private JLabel Warranty_Label;
    private JPanel Shopping_Cart_Panel;
    private JFrame Shopping_Cart_Frame;
    private JLabel Total_Label;
    private JLabel Discount_Ten_Label;
    private JLabel Discount_Twenty_Label;
    private JLabel Final_Total_Label;

    private void update_Table_Data() {
        Table_Model.setRowCount(0);

        for (Product product : Shopping_Manager.Product_Items) {
            if (Combo_Box.getSelectedItem().equals("All") ||
                    (Combo_Box.getSelectedItem().equals("Clothes") && product instanceof Clothing) ||
                    (Combo_Box.getSelectedItem().equals("Electronics") && product instanceof Electronics)) {

                Object[] rowData = {
                        product.getProductId(),
                        product.getproductName(),
                        product instanceof Clothing ? "Clothing" : "Electronics",
                        product.getprice(),
                        getProductInfo(product)
                };
                Table_Model.addRow(rowData);
            }
        }
    }

    private String getProductInfo(Product product) {
        if (product instanceof Electronics) {
            return "Brand: " + ((Electronics) product).getbrand() +
                    ", Warranty: " + ((Electronics) product).getwaranty() + " years, "+
                    "Available Items: " + product.getnumberofawalabailitems();
        } else if (product instanceof Clothing) {
            return "Size: " + ((Clothing) product).getsize() +
                    ", Color: " + ((Clothing) product).getcolour()+ ", Available Items: " +
                    product.getnumberofawalabailitems();
        } else {
            return "N/A";
        }
    }

    private Product getProductInfo(String info) {
        String[] tokens = info.split(", ");
        String type = tokens[0];

        if (type.contains("Brand")) {
            String brand = tokens[0].substring(tokens[0].indexOf(":") + 1).trim();
            String warranty = tokens[1].substring(tokens[1].indexOf(":") + 1).trim();
            return new Electronics("", "", 0, 0, brand, Double.parseDouble(warranty.split(" ")[0]));
        } else if (type.contains("Size")) {
            String size = tokens[0].substring(tokens[0].indexOf(":") + 1).trim();
            String color = tokens[1].substring(tokens[1].indexOf(":") + 1).trim();
            return new Clothing("", "", 0, 0, size, color);
        } else {
            return null;
        }
    }


    My(WestminsterShoppingManager shoppingManager) {
        this.Shopping_Manager = shoppingManager;
        this.setPreferredSize(new Dimension(1200, 720));

        String[] items = {"All", "Clothes", "Electronics"};

        Select_Category_Label = new JLabel("Select Product Category");

        Shopping_Cart_Panel = new JPanel();
        Shopping_Cart_Panel.setPreferredSize(new Dimension(500, 500));
        Shopping_Cart_Panel.setVisible(false);

        Product_ID_Label = new JLabel("   Product ID :");
        Category_Label = new JLabel("   Category :");
        Name_Label = new JLabel("   Name :");
        Size_Label = new JLabel("   Size :");
        Color_Label = new JLabel("   Colour :");
        Items_Available_Label = new JLabel();
        Brand_Label = new JLabel("   Brand :");
        Warranty_Label = new JLabel("   Warranty :");

        Total_Label = new JLabel("    Total -");
        Discount_Ten_Label= new JLabel("    First Purchase Discount (10%) -");
        Discount_Twenty_Label = new JLabel("    Three item in same Category (20%) -");
        Final_Total_Label= new JLabel("   Final Total -");
        Final_Total_Label.setFont(new Font("Arial", Font.BOLD, 14));

        Combo_Box = new JComboBox<>(items);
        Combo_Box.addActionListener(this);

        My_Button = new JButton("Add to Shopping Cart");
        My_Button.setFocusable(false);
        My_Button.addActionListener(this);

        Select_Products_Label = new JLabel("  Selected Products - Details");
        Select_Products_Label.setFont(new Font("Arial", Font.BOLD, 14));

        Shopping_Cart_Button = new JButton("Shopping Cart");
        Shopping_Cart_Button.setFocusable(false);
        Shopping_Cart_Button.addActionListener(this);

        Table_Model = new DefaultTableModel();
        Table_Model.addColumn("Product ID");
        Table_Model.addColumn("Name");
        Table_Model.addColumn("Category");
        Table_Model.addColumn("Price \u00A3");
        Table_Model.addColumn("Info");

        Product_Table = new JTable(Table_Model);
        Product_Table.setRowHeight(30);

        update_Table_Data();
        highlightLowStockProducts();

        Product_Table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = Product_Table.getSelectedRow();
            if (selectedRow != -1) {
                Object category = Table_Model.getValueAt(selectedRow, 2);

                Object productId = Table_Model.getValueAt(selectedRow, 0);
                Object productName = Table_Model.getValueAt(selectedRow, 1);
                Product product = getProductInfo(Table_Model.getValueAt(selectedRow, 4).toString());


                Product_ID_Label.setText("   Product ID: " + productId.toString());
                Name_Label.setText("   Name: " + productName.toString());
                Category_Label.setText("   Category: " + category.toString());

                if(product instanceof Clothing) {
                    Size_Label.setText("   Size :"+((Clothing) product).getsize());
                    Color_Label.setText("   Color :"+((Clothing) product).getcolour());
                } else if(product instanceof Electronics) {
                    Brand_Label.setText("   Brand :"+((Electronics) product).getbrand());
                    Warranty_Label.setText("   Warranty :"+((Electronics) product).getWarrantyPeriod()+" years");

                }

                if (category.toString().equals("Clothing")) {
                    Size_Label.setVisible(true);
                    Color_Label.setVisible(true);
                    Items_Available_Label.setVisible(true);

                    Brand_Label.setVisible(false);
                    Warranty_Label.setVisible(false);
                } else if (category.toString().equals("Electronics")) {
                    Brand_Label.setVisible(true);
                    Warranty_Label.setVisible(true);

                    Size_Label.setVisible(false);
                    Color_Label.setVisible(false);
                    Items_Available_Label.setVisible(false);
                }
            }
        });



        Product_Table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedRow = Product_Table.getSelectedRow();
                    if (selectedRow != -1) {
                        Object productId = Table_Model.getValueAt(selectedRow, 0);
                        Object productName = Table_Model.getValueAt(selectedRow, 1);

                        Product_ID_Label.setText("   Product ID: " + productId.toString());
                        Name_Label.setText("   Name: " + productName.toString());
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(Product_Table);

        this.setTitle("Westminster Shopping Center");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.blue);
        this.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(Select_Category_Label);
        northPanel.add(Combo_Box);
        northPanel.add(Shopping_Cart_Button);
        this.add(northPanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(Select_Products_Label, BorderLayout.NORTH);

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));

        labelsPanel.add(Product_ID_Label);
        labelsPanel.add(Category_Label);
        labelsPanel.add(Name_Label);
        labelsPanel.add(Size_Label);
        labelsPanel.add(Color_Label);
        labelsPanel.add(Items_Available_Label);
        labelsPanel.add(Brand_Label);
        labelsPanel.add(Warranty_Label);

        southPanel.add(labelsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(My_Button);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(southPanel, BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    private void highlightLowStockProducts() {
        Product_Table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                int availableItemsColumnIndex = 4; // Assuming available items info is in the 5th column

                Object availableItemsInfo = table.getValueAt(row, availableItemsColumnIndex);
                String availableItemsString = availableItemsInfo.toString().replaceAll("\\D+", ""); // Extracting only numbers
                int availableItems = 0;
                try {
                    availableItems = Integer.parseInt(availableItemsString);
                } catch (NumberFormatException e) {
                    // Handle the exception, you might want to set a default value or take appropriate action
                    System.err.println("Error parsing available items: " + e.getMessage());
                }

                if (availableItems < 3) {
                    cellComponent.setBackground(Color.RED);
                } else {
                    cellComponent.setBackground(table.getBackground());
                }
                return cellComponent;
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == My_Button) {
            addToShoppingCart();
        }
        if (e.getSource() == Shopping_Cart_Button) {
            openShoppingCartWindow();
        }

        if (e.getSource() == Combo_Box) {
            String selectedCategory = (String) Combo_Box.getSelectedItem();
            if (!selectedCategory.equals("All") && !selectedCategory.equals("Clothes") &&
                    !selectedCategory.equals("Electronics")) {
                System.out.println(selectedCategory);
            }
            update_Table_Data();
        }
    }

    private void addToShoppingCart() {
        if (Shopping_Cart_Table == null) {
            initializeShoppingCart();
        }
        int selectedRow = Product_Table.getSelectedRow();
        if (selectedRow != -1) {
            Object productId = Table_Model.getValueAt(selectedRow, 0);
            Object productName = Table_Model.getValueAt(selectedRow, 1);
            Object price = Table_Model.getValueAt(selectedRow, 3);

            DefaultTableModel shoppingCartTableModel = (DefaultTableModel) Shopping_Cart_Table.getModel();
            boolean itemAlreadyExists = false;

            for (int i = 0; i < shoppingCartTableModel.getRowCount(); i++) {
                String cartProductId = shoppingCartTableModel.getValueAt(i, 0).toString().split(",")[0].replace("ID: ", "").trim();
                if (cartProductId.equals(productId.toString())) {
                    // If the product already exists in the cart, update the quantity and price
                    int currentQuantity = (int) shoppingCartTableModel.getValueAt(i, 1);
                    shoppingCartTableModel.setValueAt(currentQuantity + 1, i, 1);
                    double totalPrice = (currentQuantity + 1) * Double.parseDouble(price.toString());
                    shoppingCartTableModel.setValueAt(totalPrice, i, 2);
                    itemAlreadyExists = true;
                    break;
                }
            }

            if (!itemAlreadyExists) {
                // If the product is not in the cart, add it with quantity 1
                Object[] rowData = {"ID: " + productId.toString() + ", Name: " + productName.toString(), 1, price};
                shoppingCartTableModel.addRow(rowData);
            }

            updateShoppingCartDetails();
        }
    }


    private void updateShoppingCartDetails() {
        DefaultTableModel shoppingCartTableModel = (DefaultTableModel) Shopping_Cart_Table.getModel();

        double total = 0;
        int itemCount = shoppingCartTableModel.getRowCount();

        for (int i = 0; i < itemCount; i++) {
            double price = (double) shoppingCartTableModel.getValueAt(i, 2);
            int quantity = (int) shoppingCartTableModel.getValueAt(i, 1);
            total += price * quantity;
        }

        double discountTen = total * 0.1;
        double discountTwenty = total >= 3 ? total * 0.2 : 0;
        double finalTotal = total - discountTen - discountTwenty;

        Total_Label.setText("    Total - " + String.format("%.2f", total)+" \u00A3");
        Discount_Ten_Label.setText("    First Purchase Discount (10%) - " + String.format("%.2f", discountTen)+" \u00A3");
        Discount_Twenty_Label.setText("    Three items in the same Category Discount (20%) - " + String.format("%.2f", discountTwenty)+" \u00A3");
        Final_Total_Label.setText("    Final Total - " + String.format("%.2f", finalTotal)+" \u00A3");
    }

    private void openShoppingCartWindow() {
        if (Shopping_Cart_Frame== null) {
            initializeShoppingCart();
        }

        Shopping_Cart_Frame.setVisible(true);
    }

    private void initializeShoppingCart() {
        Shopping_Cart_Frame= new JFrame("Shopping Cart");
        Shopping_Cart_Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Shopping_Cart_Frame.setSize(500, 500);
        Shopping_Cart_Frame.setLayout(new BorderLayout());

        JPanel shoppingCartPanel = new JPanel();
        shoppingCartPanel.setLayout(new BorderLayout());

        DefaultTableModel shoppingCartTableModel = new DefaultTableModel();
        shoppingCartTableModel.addColumn("Product");
        shoppingCartTableModel.addColumn("Quantity");
        shoppingCartTableModel.addColumn("Price \u00A3");

        Shopping_Cart_Table = new JTable(shoppingCartTableModel);
        JScrollPane shoppingCartScrollPane = new JScrollPane(Shopping_Cart_Table);
        Shopping_Cart_Table.setRowHeight(30);

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new GridLayout(4, 1));

        labelsPanel.add(Total_Label);
        labelsPanel.add(Discount_Ten_Label);
        labelsPanel.add(Discount_Twenty_Label);
        labelsPanel.add(Final_Total_Label);

        shoppingCartPanel.add(shoppingCartScrollPane, BorderLayout.CENTER);
        shoppingCartPanel.add(labelsPanel, BorderLayout.SOUTH);

        Shopping_Cart_Frame.add(shoppingCartPanel);
    }
}