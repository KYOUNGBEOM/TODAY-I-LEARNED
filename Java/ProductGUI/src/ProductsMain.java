import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ProductsMain extends JFrame{

	private Object[] columnNames = {"아이디", "이름", "입고수량", "단가", "카테고리", "입고일자"};
	private Object[][] rowData;
	private JTable table = null;
	private JScrollPane scrollPane = null;
	private JButton[] btn = new JButton[4];

	private JTextField txtId = new JTextField(15);
	private JTextField txtName = new JTextField(15);
	private JTextField txtStock = new JTextField(15);
	private JTextField txtPrice = new JTextField(15);
	private JTextField txtCategory = new JTextField(15);
	private JTextField txtInputdate = new JTextField(15);

	ProductsDao dao = new ProductsDao();
	ArrayList<ProductsBean> lists = null;


	ProductsMain(String title) {
		super(title);

		lists = dao.getAllProducts();
		rowData = new Object[lists.size()][columnNames.length];
		fillData();

		table = new JTable(rowData, columnNames);
		scrollPane = new JScrollPane(table);
		
		compose();

		setSize(500, 500);
		setVisible(true);
	}

	public void fillData() {

		for(int i = 0; i < lists.size(); i++) {
			int j = 0;

			rowData[i][j++] = lists.get(i).getId();
			rowData[i][j++] = lists.get(i).getName();
			rowData[i][j++] = lists.get(i).getStock();
			rowData[i][j++] = lists.get(i).getPrice();
			rowData[i][j++] = lists.get(i).getCategory();
			rowData[i][j++] = lists.get(i).getInputdate();

		}
	}

	private void compose() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		scrollPane.setBounds(0, 0, 490, 200);
		contentPane.add(scrollPane);
		
		JPanel pCenter = new JPanel();
		
		JLabel lbId = new JLabel("아이디");
		JLabel lbName = new JLabel("이름");
		JLabel lbStock = new JLabel("입고 수량");
		JLabel lbPrice = new JLabel("단가");
		JLabel lbCategory = new JLabel("카테고리");
		JLabel lbInputdate = new JLabel("입고일자");
		
		lbId.setBounds(20, 20, 100, 20);
		lbName.setBounds(20, 20*2, 100, 20);
		lbStock.setBounds(20, 20*3, 100, 20);
		lbPrice.setBounds(20, 20*4, 100, 20);
		lbCategory.setBounds(20, 20*5, 100, 20);
		lbInputdate.setBounds(20, 20*6, 100, 20);
		
		pCenter.add(lbId);
		pCenter.add(lbName);
		pCenter.add(lbStock);
		pCenter.add(lbPrice);
		pCenter.add(lbCategory);
		pCenter.add(lbInputdate);
		
		txtId.setBounds(100, 20, 100, 20);
		txtName.setBounds(100, 20*2, 100, 20);
		txtStock.setBounds(100, 20*3, 100, 20);
		txtPrice.setBounds(100, 20*4, 100, 20);
		txtCategory.setBounds(100, 20*5, 100, 20);
		txtInputdate.setBounds(100, 20*6, 100, 20);
		
		txtId.setEnabled(false);
		txtId.setText("0");
		
		pCenter.add(txtId);
		pCenter.add(txtName);
		pCenter.add(txtStock);
		pCenter.add(txtPrice);
		pCenter.add(txtCategory);
		pCenter.add(txtInputdate);
		
		pCenter.setBounds(0, 220, 500, 200);
		pCenter.setBackground(Color.pink);
		pCenter.setLayout(null);
		
		contentPane.add(pCenter);
		
		
		
		
		JPanel pSouth = new JPanel();
		
		String[] btnTitle = {"등록", "수정", "삭제", "종료"}; 
		
		for(int i = 0; i < btn.length; i++) {
			btn[i] = new JButton(btnTitle[i]);
			pSouth.add(btn[i]);
		}
		
		pSouth.setBackground(Color.yellow);
		pSouth.setLayout(new GridLayout(1,4));
		pSouth.setBounds(0,420, 500, 40);
		
		contentPane.add(pSouth);
		
	}

	public static void main(String[] args) {
		new ProductsMain("상품관리 프로그램");
		
	}
}
