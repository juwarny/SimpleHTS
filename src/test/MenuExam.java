package test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MenuExam
{
	// *** DB ���� ���� ���� ***
	
	Connection connection;
	PreparedStatement preparedStatement;
	Statement statement;
	ResultSet resultSet;
	
	
	
	// *** ��� ���� ***
	
	public final int SEARCH_ID = 0;
	public final int SEARCH_NAME = 1;
	public final int SEARCH_ADDR = 2;
	public final int SEARCH_ALL = 3;
	public final int SEARCH_NONE = 4;

	
	public final int NEW_MODE = 1;
	public final int EDIT_MODE = 2;
	
	
	
	// *** ���� UI ���� ���� ���� ***
	
	// ������ ����
	JFrame jFrame = new JFrame("DB����");
	
	
	//DefaultTableModel�� �����ϰ� ������ ���
	DefaultTableModel defaultTableModel = new DefaultTableModel(new String[] { "ID", "Name", "Age", "Address" }, 0)
	{
		@Override
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
		
	};

	//JTable�� DefaultTableModel�� ���
	JTable jTable = new JTable(defaultTableModel);
	
	// Swing���� ��ũ�ѹٸ� �ֱ� ���� �Ʒ��� ���� ����Ѵ�.
	JScrollPane jScrollPane = new JScrollPane(jTable);// ��ũ�ѹ� �����

	// �˻�� �Է��� �Է�â
	JTextField jTextField = new JTextField(20);
	
	// �˻� ��ư
	JButton jButton = new JButton("�˻�");
	
	// �޺� �ڽ�
	JComboBox<String> jComboBox = new JComboBox<String>(new String[] {"ID", "Name", "Address", "��ü�˻�"}); 
	
	// �޴� ����
	JMenuBar menuBar = new JMenuBar();
	JMenu adminMenu = new JMenu("����");
	
	// �г� ����
	JPanel jPanel = new JPanel(new FlowLayout());

	
	/**
	 * [MenuExam Ŭ������ ������]
	 * GUI�� �����ϰ� DB�� ���̺� ���� ���θ� Ȯ���� ���� ��� �����Ѵ�.
	 * ��ü ��� ����� ������ִ� �޼ҵ带 ȣ���Ѵ�.
	 */
	public MenuExam()
	{
		// *** ���� UI ���� ***
		
		// ���� �޴� ����
		adminMenu.add(new JMenuItem("����"));
		adminMenu.add(new JMenuItem("����"));
		adminMenu.add(new JMenuItem("����"));
		adminMenu.addSeparator(); //���м� �߰�
		adminMenu.add(new JMenuItem("����"));
		
		// �޴��� �޴��ٿ� ���
		menuBar.add(adminMenu);

		// �޴��� �߰�
		jFrame.setJMenuBar(menuBar);
		
		// ���̺� �߰�
		jTable.getTableHeader().setReorderingAllowed(false); //���̺� �÷� ���� ���� ����
		jFrame.add(jScrollPane);
				
		// �ϴ� �гο� ������Ʈ �߰�
		jPanel.add(jComboBox);
		jPanel.add(jTextField);
		jPanel.add(jButton);
		
		// �ϴ� �г� �߰�
		jFrame.add(jPanel, "South");
		
		// ������ ũ�� �� ���̱� ����
		jFrame.setSize(600, 400);
		jFrame.setResizable(false);
		jFrame.setVisible(true);
		jFrame.setLocationRelativeTo(null);

		// swing���� �ִ� X��ư Ŭ���� ����
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		// *** DB ���� ***
		
		try
		{
			// �� �ε�
			Class.forName(DatabaseConstant.DRIVERNAME);
			 

			
			
			// *** DB ���� ***
			
			// �� ����
			connection = DriverManager.getConnection(DatabaseConstant.URL, DatabaseConstant.USER, DatabaseConstant.PASSWORD);
 
			// �� ���� [Statement]
			statement = connection.createStatement();
 
			// �� ���� [CRUD]
			resultSet = statement.executeQuery("SELECT COUNT(TABLE_NAME) FROM USER_TABLES WHERE TABLE_NAME='MEMBER'");
			
			int result = 0;
			
			if(resultSet.next())
			{
				result = Integer.valueOf(resultSet.getString(1));
			}
			
			// ���̺��� �������� ���� ��� ����!
			if( result == 0 )
			{
				statement.executeUpdate("CREATE TABLE MEMBER ( ID VARCHAR2(30) PRIMARY KEY NOT NULL, NAME VARCHAR2(20) NOT NULL, AGE NUMBER(3) NOT NULL, ADDR VARCHAR2(100) NOT NULL )");
			}
			// ���̺��� �����ϴ� ��� ������ ������ ����ϱ�
			else
			{
				searchMember(SEARCH_NONE, null);
			}
			 
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("[�ε� ����]\n" + e.getStackTrace());
		}
		catch (SQLException e)
		{
			System.out.println("[���� ����]\n" +  e.getStackTrace());
		}
		
		finally
		{
			closeDatabase();
		}
		
		
		
		
		
		// *** �̺�Ʈ ���� ***
		
		//���� �޴�
		adminMenu.getItem(0).addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//���̾�α� ����
				MemberDialog memberDialog = new MemberDialog();
				memberDialog.jDialog.setModal(true);
				memberDialog.jDialog.setVisible(true);
			}			
		});

		//���� �޴�
		adminMenu.getItem(1).addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//���� �÷��� �����ϴ� ���� �������� ���� ��츦 ���
				switch( jTable.getSelectedRowCount() )
				{
					case 0:
						JOptionPane.showMessageDialog(jFrame, "���õ� �÷��� �����ϴ�.");
					break;
					
					case 1:
						
						//DefaultTableModel���� ������ �÷��� ������ ��������
						String id = String.valueOf(defaultTableModel.getValueAt(jTable.getSelectedRow(), 0));
						String name = String.valueOf(defaultTableModel.getValueAt(jTable.getSelectedRow(), 1));
						int age = Integer.valueOf((String) defaultTableModel.getValueAt(jTable.getSelectedRow(), 2));
						String addr = String.valueOf(defaultTableModel.getValueAt(jTable.getSelectedRow(), 3));
						
						//���̾�α� ����
						MemberDialog memberDialog = new MemberDialog(id, name, age, addr);
						memberDialog.jDialog.setModal(true);
						memberDialog.jDialog.setVisible(true);
					break;
					
					default:
						JOptionPane.showMessageDialog(jFrame, "�ϳ��� �÷��� �������ּ���.");
					break;
				}
			}			
		});
		
		//���� �޴�
		adminMenu.getItem(2).addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//�ϳ� �̻��� �÷��� ���õ� ���
				if( jTable.getSelectedRowCount() > 0 )
				{
					if( JOptionPane.showConfirmDialog(jFrame, "���õ� �÷����� �����մϱ�?", "���� Ȯ��", 0) == 0 )
					{
						//���õ� ������� ����
						for(int i : jTable.getSelectedRows())
						{
							deleteMember(String.valueOf(defaultTableModel.getValueAt(i, 0)));
						}
						
						//���� ������Ʈ
						searchMember(SEARCH_NONE, null);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(jFrame, "���õ� �÷��� �����ϴ�.");
				}
		
				
			}			
		});
		
		//���� �޴�
		adminMenu.getItem(4).addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if( JOptionPane.showConfirmDialog(MenuExam.this.jFrame, "�����մϴ�.", "���� Ȯ��", 0) == 0 )
				{
					System.exit(0);
				}

			}			
		});
		
		
		//�˻� ��ư
		jButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(jButton.getText().equals("�˻�"))
				{
				
					if( jTextField.getText().trim().length() > 0 )
					{
	
						switch( jComboBox.getSelectedIndex() )
						{
							//���̵�
							case SEARCH_ID:
								searchMember(SEARCH_ID, jTextField.getText().trim());
							break;
	
								
							//�̸�
							case SEARCH_NAME:
								searchMember(SEARCH_NAME, jTextField.getText().trim());
							break;
							
							
							//�ּ�
							case SEARCH_ADDR:
								searchMember(SEARCH_ADDR, jTextField.getText().trim());
							break;
							
							//��ü
							case SEARCH_ALL:
								searchMember(SEARCH_ALL, jTextField.getText().trim());
							break;
						}
						
						jButton.setText("���");
					}
					else
					{
						JOptionPane.showMessageDialog(jFrame, "�˻�� �Է����ּ���!");
					}
				}
				
				else
				{
					//�˻� �ʱ�ȭ
					searchMember(SEARCH_NONE, null);
				}

			}			
		});
		

		
	}
	
	
	
	/**
	 * [�����ͺ��̽��� �ݴ� �޼ҵ�]
	 * connection, statement, resultSet�� null ���θ� üũ�ѵ� ������� ��� �ݾ��ش�.
	 * @param ����
	 * @return ����
	 */
	public void closeDatabase()
	{
		try
		{
			if( connection != null )
			{
				connection.close();
			}
			 
			if( statement != null )
			{
				statement.close();
			}
			 
			if( resultSet != null )
			{
				resultSet.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println("[�ݱ� ����]\n" +  e.getStackTrace());
		}
	}
	
	
	
	
	/**
	 * ID �ߺ� üũ �޼ҵ�
	 * �Ķ���ͷ� �Է¹��� id���� member ���̺��� id �ʵ忡�� �˻��ؼ� 1���� �����ϸ� true�� �����ϴ� �޼ҵ�
	 * @param id ���̵�
	 * @return �ߺ��Ǵ� id�� ������ ��� true, ������ false
	 */
	public boolean idCheck(String id)
	{
		int result = 0;
		
		try
		{
			// �� ����
			connection = DriverManager.getConnection(DatabaseConstant.URL, DatabaseConstant.USER, DatabaseConstant.PASSWORD);
 
			// �� ���� [Statement]
			statement = connection.createStatement();
 
			// �� ���� [CRUD]
			resultSet = statement.executeQuery("SELECT COUNT(id) FROM MEMBER WHERE ID='"+ id + "'");
			
			if(resultSet.next())
			{
				result = Integer.valueOf(resultSet.getString(1));
			}
			 
		}
		catch (SQLException e)
		{
			System.out.println("[���� ����]\n" +  e.getStackTrace());
		}
		
		finally
		{
			closeDatabase();
		}
		
		return (result > 0) ? true : false;
	}
	
	
	/**
	 * [��� �˻� �� ��� �޼ҵ�]
	 * searchMode�� ���� ��� ����ϰų�, ID,�̸�,�ּҰ� keyWord�� ��ġ�ϴ� ����� ������ش�.
	 * @param searchMode �˻� ���
	 * @param keyWord �˻���
	 * @return ����
	 */
	public void searchMember(int searchMode, String keyWord)
	{
		try
		{
			// �� ����
			connection = DriverManager.getConnection(DatabaseConstant.URL, DatabaseConstant.USER, DatabaseConstant.PASSWORD);
			
			// �� ���� [Statement]
			statement = connection.createStatement();
 
			// �� ���� [CRUD]
			
			switch( searchMode )
			{
				//���̵�
				case SEARCH_ID:
					resultSet = statement.executeQuery("SELECT * FROM MEMBER WHERE ID LIKE '%" + keyWord + "%'");
				break;

				//�̸�
				case SEARCH_NAME:
					resultSet = statement.executeQuery("SELECT * FROM MEMBER WHERE NAME LIKE '%" + keyWord + "%'");
				break;
				
				
				//�ּ�
				case SEARCH_ADDR:
					resultSet = statement.executeQuery("SELECT * FROM MEMBER WHERE ADDR LIKE '%" + keyWord + "%'");
				break;
				
				//�̸�, �ּ�, ���̵𿡼� �˻�
				case SEARCH_ALL:
					resultSet = statement.executeQuery("SELECT DISTINCT * FROM MEMBER WHERE ID LIKE '%" + keyWord + "%' OR NAME LIKE '%" + keyWord + "%' OR ADDR LIKE '%" + keyWord + "%'");
				break;
				
				//�˻� ����
				case SEARCH_NONE:
					resultSet = statement.executeQuery("SELECT * FROM MEMBER");
					
					//���� �˻� ��Ȳ�� �ʱ�ȭ
					jTextField.setText(null);
					jButton.setText("�˻�");
					jComboBox.setSelectedIndex(0);
				break;
				
			}
			
			// �÷� ���� ��������
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			// �����͸� ���� ������Ʈ ����
			Object [] tempObject = new Object[resultSetMetaData.getColumnCount()];
			
			// DefaultTableModel �ʱ�ȭ
			defaultTableModel.setRowCount(0);
			
			while (resultSet.next())
			{
				for(int i=0; i < resultSetMetaData.getColumnCount(); i++)
				{
					tempObject[i] = resultSet.getString(i+1);
				}
				
				defaultTableModel.addRow(tempObject);
			}
			
			if( defaultTableModel.getRowCount() > 0 )
			{
				jTable.setRowSelectionInterval(0, 0); //ù°�ٿ� ��Ŀ��
			}
			
			
		}
		catch (SQLException e)
		{
			System.out.println("[���� ����]\n" +  e.getStackTrace());
		}
		
		finally
		{
			closeDatabase();
		}
		
	}

	
	
	/**
	 * [��� �߰� �޼ҵ�]
	 * dialogMode�� NEW_MODE���� EDIT_MODE������ üũ�� �� ID, �̸�, ����, �ּҰ��� �޾ƿͼ� ���� �߰��ϰų� �����ϴ� �޼ҵ�
	 * @param dialogMode NEW_MODE���� EDIT_MODE������ üũ�ϴ� ����
	 * @param id ���̵�
	 * @param name �̸�
	 * @param age ����
	 * @param addr �ּ�
	 * @return ���������� �����ǰų� �߰��Ǹ� true, �����ϸ� false�� ��ȯ�Ѵ�.
	 */
	public boolean submitMember(int dialogMode, String id, String name, int age, String addr)
	{
		
		int result = 0;
		
		try
		{
			// �� ����
			connection = DriverManager.getConnection(DatabaseConstant.URL, DatabaseConstant.USER, DatabaseConstant.PASSWORD);
 
			// �� ���� [CRUD]
			if(dialogMode == NEW_MODE)
			{
				preparedStatement = connection.prepareStatement("INSERT INTO MEMBER VALUES ( ?, ?, ?, ? )");
				preparedStatement.setString(1, id);
				preparedStatement.setString(2, name);
				preparedStatement.setInt(3, age);
				preparedStatement.setString(4, addr);
			}
			else
			{
				preparedStatement = connection.prepareStatement("UPDATE MEMBER SET NAME=?, AGE=?, ADDR=? WHERE ID=?");
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, age);
				preparedStatement.setString(3, addr);
				preparedStatement.setString(4, id);
			}
			
			
			// �� ���� [CRUD]
			result = preparedStatement.executeUpdate();
			 
		}
		catch (SQLException e)
		{
			System.out.println("[���� ����]\n" +  e.getStackTrace());
		}
		
		finally
		{
			closeDatabase();
		}
		
		//���� ������Ʈ
		searchMember(SEARCH_NONE, null);
		
		return (result > 0) ? true : false;
	}

	
	
	// *** ��� ���� �޼ҵ� ***
	/**
	 * [��� ���� �޼ҵ�]
	 * ����� ID�� String���� �޾ƿͼ� �����Ѵ�.
	 * @param id ���̵�
	 * @return ��� ������ �����ϸ� true, �����ϸ� false
	 */
	public boolean deleteMember(String id)
	{
		
		int result = 0;
		
		try
		{
			// �� ����
			connection = DriverManager.getConnection(DatabaseConstant.URL, DatabaseConstant.USER, DatabaseConstant.PASSWORD);
 
			// �� ���� [CRUD]
			preparedStatement = connection.prepareStatement("DELETE FROM MEMBER WHERE ID=?");
			preparedStatement.setString(1, id);

			result = preparedStatement.executeUpdate();
			 
		}
		catch (SQLException e)
		{
			System.out.println("[���� ����]\n" +  e.getStackTrace());
		}
		
		finally
		{
			closeDatabase();
		}
		
		return (result > 0) ? true : false;
	}	
	
	
	
	public static void main(String[] args)
	{
		//����
		new MenuExam();

	}

	
	
	
	
	// *** ���̾�α� Ŭ���� ***
	/**
	 * ����/���� ���� �̺�Ʈ�� ������ JDialog�� �����ϴ� Ŭ���� 
	 * @author ������
	 */
	class MemberDialog
	{
		// *** ���̾�α� UI ���� ���� ���� ***
		
		JDialog jDialog = new JDialog(jFrame, "��� �Ӽ� �Է�");
		JButton idCheckButton = new JButton("ID üũ Ȯ��");
		JButton submitButton = new JButton("����");
		JButton cancelButton = new JButton("���");
		
		JPanel fieldNamePanel = new JPanel(new GridLayout(4,1));
		JPanel fieldValuePanel = new JPanel(new GridLayout(4,1));
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JPanel idPanel = new JPanel(new BorderLayout());
		
		JLabel idLabel = new JLabel("ID");
		JLabel nameLabel = new JLabel("�̸�");
		JLabel ageLabel = new JLabel("����");
		JLabel addrLabel = new JLabel("�ּ�");
		
		JTextField idField = new JTextField(10);
		JTextField nameField = new JTextField(10);
		JTextField ageField = new JTextField(10);
		JTextField addrField = new JTextField(10);
		
		
		// *** ���̾�α� ���� ���� ���� ***
		
		int dialogMode = NEW_MODE;

		
		
		/**
		 * [MemberDialog ������]
		 * �Ķ���Ͱ� �ƹ��͵� ���� ���, ���� ���� �ν��Ѵ�.
		 * ���̾�α� ���� ��� ������ �� ������, modal ������ ����
		 * modal ������ visible�� ȣ���� ������ ó���Ѵ�.
		 */
		MemberDialog()
		{
			// *** ���̾�α� UI ���� ***
			
			//�гο� ������Ʈ �߰�
			fieldNamePanel.add(idLabel);
			fieldNamePanel.add(nameLabel);
			fieldNamePanel.add(ageLabel);
			fieldNamePanel.add(addrLabel);

			idPanel.add(idField);
			idPanel.add(idCheckButton, "East");
			
			fieldValuePanel.add(idPanel);
			fieldValuePanel.add(nameField);
			fieldValuePanel.add(ageField);
			fieldValuePanel.add(addrField);
			
			buttonPanel.add(submitButton);
			buttonPanel.add(cancelButton);
			

			// ���̾�α׿� �г� �߰�
			jDialog.add(fieldNamePanel, "West");
			jDialog.add(fieldValuePanel, "Center");
			jDialog.add(buttonPanel, "South");


			// ���̾�α��� ũ��� ���� ����
			jDialog.setSize(350, 250);
			jDialog.setLocationRelativeTo(jFrame);
			jDialog.setResizable(false);
			jDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
			// *** �̺�Ʈ ���� ***
			
			//ID üũ �޴�
			idCheckButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					//ID�� �Է��� ��쿡��
					if(idField.getText().length() > 0)
					{
						//���̵� �����ϴ� ���
						if(MenuExam.this.idCheck(idField.getText()))
						{
							JOptionPane.showMessageDialog(jDialog, "����� �� ���� ���̵��Դϴ�!");
							
							idField.setText(null);
							idField.requestFocus();
						}
						//���� ���̵��� ���
						else
						{
							JOptionPane.showMessageDialog(jDialog, "��� ������ ���̵��Դϴ�!");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(jDialog, "���̵� �Է����ּ���!");
						idField.requestFocus();
					}
				}			
			});
			
			
			//����(����) �޴�
			submitButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					//ID�� �Է��� ��쿡��
					if(idField.getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(jDialog, "���̵� �Է����ּ���!");
						idField.requestFocus();
					}
					else if(idField.getText().trim().length() > 30)
					{
						JOptionPane.showMessageDialog(jDialog, "���̵�� 30�� �̳��� �Է��ؾ� �մϴ�!");
						idField.requestFocus();
					}
					else if(dialogMode == NEW_MODE && MenuExam.this.idCheck(idField.getText()))
					{
						JOptionPane.showMessageDialog(jDialog, "����� �� ���� ���̵��Դϴ�!");
						
						idField.setText(null);
						idField.requestFocus();
					}
					else if(nameField.getText().trim().length() == 0 )
					{
						JOptionPane.showMessageDialog(jDialog, "�̸��� �Է����ּ���!");
						nameField.requestFocus();
					}
					else if(nameField.getText().trim().length() > 20)
					{
						JOptionPane.showMessageDialog(jDialog, "�̸� 20�� �̳��� �Է��ؾ� �մϴ�!");
						nameField.requestFocus();
					}
					else if(ageField.getText().trim().length() == 0 )
					{
						JOptionPane.showMessageDialog(jDialog, "���̸� �Է����ּ���!");
						ageField.requestFocus();
					}
					else if(!isNumber(ageField.getText().trim()))
					{
						JOptionPane.showMessageDialog(jDialog, "���̸� ���ڷ� �Է����ּ���!");
						
						ageField.setText(null);
						ageField.requestFocus();
					}
					else if(Integer.valueOf(ageField.getText()) < 0 || Integer.valueOf(ageField.getText()) > 999)
					{
						JOptionPane.showMessageDialog(jDialog, "���̴� 0���� 999������ ���� �Է°����մϴ�.!");
						ageField.requestFocus();
					}
					else if(addrField.getText().trim().length() == 0 )
					{
						JOptionPane.showMessageDialog(jDialog, "�ּҸ� �Է����ּ���!");
						addrField.requestFocus();
					}
					else if(addrField.getText().trim().length() > 100 )
					{
						JOptionPane.showMessageDialog(jDialog, "�ּҴ� 100�� �̳��� �Է��ؾ� �մϴ�!");
						addrField.requestFocus();
					}
					
					//������ ���� ���
					else
					{
						if(MenuExam.this.submitMember(dialogMode, idField.getText().trim(), nameField.getText().trim(), Integer.valueOf(ageField.getText()), addrField.getText().trim()))
						{
							if(dialogMode == NEW_MODE)
							{
								JOptionPane.showMessageDialog(jDialog, "������ ���ϵ帳�ϴ�!");
							}
							else
							{
								JOptionPane.showMessageDialog(jDialog, "���������� �����Ǿ����ϴ�!");
							}
							
							jDialog.dispose(); //�ݱ�
						}
						//���� ���̵��� ���
						else
						{
							JOptionPane.showMessageDialog(jDialog, "�Է� �������� ������ �߻��߽��ϴ�!");							
						}

						
					}
				}			
			});
			

			//��� �޴�
			cancelButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					jDialog.dispose(); //�ݱ�
				}			
			});
			
		}
		
		
		/**
		 * [MemberDialog ������]
		 * �Ķ���Ͱ� �ִ� ���, ���� ���� �ν��Ѵ�.
		 * �Ķ���Ͱ� ���� �����ڸ� ȣ���� �ش� ������ ��� �����ϰ�
		 * ��������̹Ƿ� �Ķ���ͷ� �޾ƿ� �������� �ʵ忡 ������ش�.
		 * ���̾�α� ���� ��� ������ �� ������, modal ������ ����
		 * modal ������ visible�� ȣ���� ������ ó���Ѵ�.
		 * @param id ���̵�
		 * @param name �̸�
		 * @param age ����
		 * @param addr �ּ�
		 */
		MemberDialog(String id, String name, int age, String addr)
		{
			this();
			
			//��� ����
			dialogMode = EDIT_MODE;
			
			//���� �� ����
			idField.setText(id);
			nameField.setText(name);
			ageField.setText(String.valueOf(age));
			addrField.setText(addr);
			
			//ID ��Ȱ��ȭ
			idField.setEnabled(false);
			idCheckButton.setEnabled(false);
			
			//��ư ����
			submitButton.setText("����");
		}
		
		
		
		/**
		 * [�������� �Ǻ����ִ� �޼ҵ�]
		 * String���� �޾� Integer������ ��ȯ �׽�Ʈ�Ͽ� ���� �� ��� false, ������ ��� true�� �����Ѵ�.
		 * @param s ������ ���ڿ�
		 * @return �����ϰ�� true, �ƴҰ�� false
		 */
		public boolean isNumber(String s)
		{
			
			try
			{
				//���ڷ� �ٲ�µ� ������ ������ true
				Integer.valueOf(s);
				return true;
			}
			catch(NumberFormatException e)
			{
				//������ ����� ���ڰ� �ƴϹǷ� false
				return false;
			}
		}
	}
}
