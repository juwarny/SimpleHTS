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
	// *** DB 관련 변수 선언 ***
	
	Connection connection;
	PreparedStatement preparedStatement;
	Statement statement;
	ResultSet resultSet;
	
	
	
	// *** 상수 선언 ***
	
	public final int SEARCH_ID = 0;
	public final int SEARCH_NAME = 1;
	public final int SEARCH_ADDR = 2;
	public final int SEARCH_ALL = 3;
	public final int SEARCH_NONE = 4;

	
	public final int NEW_MODE = 1;
	public final int EDIT_MODE = 2;
	
	
	
	// *** 메인 UI 관련 변수 선언 ***
	
	// 프레임 선언
	JFrame jFrame = new JFrame("DB연동");
	
	
	//DefaultTableModel을 선언하고 데이터 담기
	DefaultTableModel defaultTableModel = new DefaultTableModel(new String[] { "ID", "Name", "Age", "Address" }, 0)
	{
		@Override
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
		
	};

	//JTable에 DefaultTableModel을 담기
	JTable jTable = new JTable(defaultTableModel);
	
	// Swing에서 스크롤바를 넣기 위해 아래와 같이 사용한다.
	JScrollPane jScrollPane = new JScrollPane(jTable);// 스크롤바 만들기

	// 검색어를 입력할 입력창
	JTextField jTextField = new JTextField(20);
	
	// 검색 버튼
	JButton jButton = new JButton("검색");
	
	// 콤보 박스
	JComboBox<String> jComboBox = new JComboBox<String>(new String[] {"ID", "Name", "Address", "전체검색"}); 
	
	// 메뉴 선언
	JMenuBar menuBar = new JMenuBar();
	JMenu adminMenu = new JMenu("관리");
	
	// 패널 선언
	JPanel jPanel = new JPanel(new FlowLayout());

	
	/**
	 * [MenuExam 클래스의 생성자]
	 * GUI를 생성하고 DB에 테이블 존재 여부를 확인해 없을 경우 생성한다.
	 * 전체 멤버 목록을 출력해주는 메소드를 호출한다.
	 */
	public MenuExam()
	{
		// *** 메인 UI 설정 ***
		
		// 관리 메뉴 생성
		adminMenu.add(new JMenuItem("가입"));
		adminMenu.add(new JMenuItem("수정"));
		adminMenu.add(new JMenuItem("삭제"));
		adminMenu.addSeparator(); //구분선 추가
		adminMenu.add(new JMenuItem("종료"));
		
		// 메뉴를 메뉴바에 등록
		menuBar.add(adminMenu);

		// 메뉴바 추가
		jFrame.setJMenuBar(menuBar);
		
		// 테이블 추가
		jTable.getTableHeader().setReorderingAllowed(false); //테이블 컬럼 순서 변경 금지
		jFrame.add(jScrollPane);
				
		// 하단 패널에 컴포넌트 추가
		jPanel.add(jComboBox);
		jPanel.add(jTextField);
		jPanel.add(jButton);
		
		// 하단 패널 추가
		jFrame.add(jPanel, "South");
		
		// 프레임 크기 및 보이기 설정
		jFrame.setSize(600, 400);
		jFrame.setResizable(false);
		jFrame.setVisible(true);
		jFrame.setLocationRelativeTo(null);

		// swing에만 있는 X버튼 클릭시 종료
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		// *** DB 연결 ***
		
		try
		{
			// ① 로드
			Class.forName(DatabaseConstant.DRIVERNAME);
			 

			
			
			// *** DB 생성 ***
			
			// ② 연결
			connection = DriverManager.getConnection(DatabaseConstant.URL, DatabaseConstant.USER, DatabaseConstant.PASSWORD);
 
			// ② 연결 [Statement]
			statement = connection.createStatement();
 
			// ③ 실행 [CRUD]
			resultSet = statement.executeQuery("SELECT COUNT(TABLE_NAME) FROM USER_TABLES WHERE TABLE_NAME='MEMBER'");
			
			int result = 0;
			
			if(resultSet.next())
			{
				result = Integer.valueOf(resultSet.getString(1));
			}
			
			// 테이블이 생성되지 않은 경우 생성!
			if( result == 0 )
			{
				statement.executeUpdate("CREATE TABLE MEMBER ( ID VARCHAR2(30) PRIMARY KEY NOT NULL, NAME VARCHAR2(20) NOT NULL, AGE NUMBER(3) NOT NULL, ADDR VARCHAR2(100) NOT NULL )");
			}
			// 테이블이 존재하는 경우 데이터 가져와 출력하기
			else
			{
				searchMember(SEARCH_NONE, null);
			}
			 
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("[로드 오류]\n" + e.getStackTrace());
		}
		catch (SQLException e)
		{
			System.out.println("[연결 오류]\n" +  e.getStackTrace());
		}
		
		finally
		{
			closeDatabase();
		}
		
		
		
		
		
		// *** 이벤트 설정 ***
		
		//가입 메뉴
		adminMenu.getItem(0).addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//다이얼로그 띄우기
				MemberDialog memberDialog = new MemberDialog();
				memberDialog.jDialog.setModal(true);
				memberDialog.jDialog.setVisible(true);
			}			
		});

		//수정 메뉴
		adminMenu.getItem(1).addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//여러 컬럼을 선택하는 경우와 선택하지 않은 경우를 고려
				switch( jTable.getSelectedRowCount() )
				{
					case 0:
						JOptionPane.showMessageDialog(jFrame, "선택된 컬럼이 없습니다.");
					break;
					
					case 1:
						
						//DefaultTableModel에서 선택한 컬럼의 값들을 가져오기
						String id = String.valueOf(defaultTableModel.getValueAt(jTable.getSelectedRow(), 0));
						String name = String.valueOf(defaultTableModel.getValueAt(jTable.getSelectedRow(), 1));
						int age = Integer.valueOf((String) defaultTableModel.getValueAt(jTable.getSelectedRow(), 2));
						String addr = String.valueOf(defaultTableModel.getValueAt(jTable.getSelectedRow(), 3));
						
						//다이얼로그 띄우기
						MemberDialog memberDialog = new MemberDialog(id, name, age, addr);
						memberDialog.jDialog.setModal(true);
						memberDialog.jDialog.setVisible(true);
					break;
					
					default:
						JOptionPane.showMessageDialog(jFrame, "하나의 컬럼만 선택해주세요.");
					break;
				}
			}			
		});
		
		//삭제 메뉴
		adminMenu.getItem(2).addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//하나 이상의 컬럼이 선택된 경우
				if( jTable.getSelectedRowCount() > 0 )
				{
					if( JOptionPane.showConfirmDialog(jFrame, "선택된 컬럼들을 삭제합니까?", "삭제 확인", 0) == 0 )
					{
						//선택된 멤버들을 삭제
						for(int i : jTable.getSelectedRows())
						{
							deleteMember(String.valueOf(defaultTableModel.getValueAt(i, 0)));
						}
						
						//내용 업데이트
						searchMember(SEARCH_NONE, null);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(jFrame, "선택된 컬럼이 없습니다.");
				}
		
				
			}			
		});
		
		//종료 메뉴
		adminMenu.getItem(4).addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if( JOptionPane.showConfirmDialog(MenuExam.this.jFrame, "종료합니다.", "종료 확인", 0) == 0 )
				{
					System.exit(0);
				}

			}			
		});
		
		
		//검색 버튼
		jButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(jButton.getText().equals("검색"))
				{
				
					if( jTextField.getText().trim().length() > 0 )
					{
	
						switch( jComboBox.getSelectedIndex() )
						{
							//아이디
							case SEARCH_ID:
								searchMember(SEARCH_ID, jTextField.getText().trim());
							break;
	
								
							//이름
							case SEARCH_NAME:
								searchMember(SEARCH_NAME, jTextField.getText().trim());
							break;
							
							
							//주소
							case SEARCH_ADDR:
								searchMember(SEARCH_ADDR, jTextField.getText().trim());
							break;
							
							//전체
							case SEARCH_ALL:
								searchMember(SEARCH_ALL, jTextField.getText().trim());
							break;
						}
						
						jButton.setText("취소");
					}
					else
					{
						JOptionPane.showMessageDialog(jFrame, "검색어를 입력해주세요!");
					}
				}
				
				else
				{
					//검색 초기화
					searchMember(SEARCH_NONE, null);
				}

			}			
		});
		

		
	}
	
	
	
	/**
	 * [데이터베이스를 닫는 메소드]
	 * connection, statement, resultSet의 null 여부를 체크한뒤 담겨있을 경우 닫아준다.
	 * @param 없음
	 * @return 없음
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
			System.out.println("[닫기 오류]\n" +  e.getStackTrace());
		}
	}
	
	
	
	
	/**
	 * ID 중복 체크 메소드
	 * 파라메터로 입력받은 id값을 member 테이블의 id 필드에서 검색해서 1개라도 존재하면 true를 리턴하는 메소드
	 * @param id 아이디
	 * @return 중복되는 id가 존재할 경우 true, 없으면 false
	 */
	public boolean idCheck(String id)
	{
		int result = 0;
		
		try
		{
			// ② 연결
			connection = DriverManager.getConnection(DatabaseConstant.URL, DatabaseConstant.USER, DatabaseConstant.PASSWORD);
 
			// ② 연결 [Statement]
			statement = connection.createStatement();
 
			// ③ 실행 [CRUD]
			resultSet = statement.executeQuery("SELECT COUNT(id) FROM MEMBER WHERE ID='"+ id + "'");
			
			if(resultSet.next())
			{
				result = Integer.valueOf(resultSet.getString(1));
			}
			 
		}
		catch (SQLException e)
		{
			System.out.println("[연결 오류]\n" +  e.getStackTrace());
		}
		
		finally
		{
			closeDatabase();
		}
		
		return (result > 0) ? true : false;
	}
	
	
	/**
	 * [멤버 검색 및 출력 메소드]
	 * searchMode에 따라 모두 출력하거나, ID,이름,주소가 keyWord와 일치하는 멤버를 출력해준다.
	 * @param searchMode 검색 모드
	 * @param keyWord 검색어
	 * @return 없음
	 */
	public void searchMember(int searchMode, String keyWord)
	{
		try
		{
			// ② 연결
			connection = DriverManager.getConnection(DatabaseConstant.URL, DatabaseConstant.USER, DatabaseConstant.PASSWORD);
			
			// ② 연결 [Statement]
			statement = connection.createStatement();
 
			// ③ 실행 [CRUD]
			
			switch( searchMode )
			{
				//아이디
				case SEARCH_ID:
					resultSet = statement.executeQuery("SELECT * FROM MEMBER WHERE ID LIKE '%" + keyWord + "%'");
				break;

				//이름
				case SEARCH_NAME:
					resultSet = statement.executeQuery("SELECT * FROM MEMBER WHERE NAME LIKE '%" + keyWord + "%'");
				break;
				
				
				//주소
				case SEARCH_ADDR:
					resultSet = statement.executeQuery("SELECT * FROM MEMBER WHERE ADDR LIKE '%" + keyWord + "%'");
				break;
				
				//이름, 주소, 아이디에서 검색
				case SEARCH_ALL:
					resultSet = statement.executeQuery("SELECT DISTINCT * FROM MEMBER WHERE ID LIKE '%" + keyWord + "%' OR NAME LIKE '%" + keyWord + "%' OR ADDR LIKE '%" + keyWord + "%'");
				break;
				
				//검색 안함
				case SEARCH_NONE:
					resultSet = statement.executeQuery("SELECT * FROM MEMBER");
					
					//기존 검색 상황도 초기화
					jTextField.setText(null);
					jButton.setText("검색");
					jComboBox.setSelectedIndex(0);
				break;
				
			}
			
			// 컬럼 정보 가져오기
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			// 데이터를 담을 오브젝트 생성
			Object [] tempObject = new Object[resultSetMetaData.getColumnCount()];
			
			// DefaultTableModel 초기화
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
				jTable.setRowSelectionInterval(0, 0); //첫째줄에 포커싱
			}
			
			
		}
		catch (SQLException e)
		{
			System.out.println("[연결 오류]\n" +  e.getStackTrace());
		}
		
		finally
		{
			closeDatabase();
		}
		
	}

	
	
	/**
	 * [멤버 추가 메소드]
	 * dialogMode가 NEW_MODE인지 EDIT_MODE인지를 체크한 후 ID, 이름, 나이, 주소값을 받아와서 새로 추가하거나 저장하는 메소드
	 * @param dialogMode NEW_MODE인지 EDIT_MODE인지를 체크하는 변수
	 * @param id 아이디
	 * @param name 이름
	 * @param age 나이
	 * @param addr 주소
	 * @return 정상적으로 수정되거나 추가되면 true, 실패하면 false를 반환한다.
	 */
	public boolean submitMember(int dialogMode, String id, String name, int age, String addr)
	{
		
		int result = 0;
		
		try
		{
			// ② 연결
			connection = DriverManager.getConnection(DatabaseConstant.URL, DatabaseConstant.USER, DatabaseConstant.PASSWORD);
 
			// ③ 실행 [CRUD]
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
			
			
			// ③ 실행 [CRUD]
			result = preparedStatement.executeUpdate();
			 
		}
		catch (SQLException e)
		{
			System.out.println("[연결 오류]\n" +  e.getStackTrace());
		}
		
		finally
		{
			closeDatabase();
		}
		
		//내용 업데이트
		searchMember(SEARCH_NONE, null);
		
		return (result > 0) ? true : false;
	}

	
	
	// *** 멤버 삭제 메소드 ***
	/**
	 * [멤버 삭제 메소드]
	 * 멤버의 ID를 String으로 받아와서 삭제한다.
	 * @param id 아이디
	 * @return 멤버 삭제가 성공하면 true, 실패하면 false
	 */
	public boolean deleteMember(String id)
	{
		
		int result = 0;
		
		try
		{
			// ② 연결
			connection = DriverManager.getConnection(DatabaseConstant.URL, DatabaseConstant.USER, DatabaseConstant.PASSWORD);
 
			// ③ 실행 [CRUD]
			preparedStatement = connection.prepareStatement("DELETE FROM MEMBER WHERE ID=?");
			preparedStatement.setString(1, id);

			result = preparedStatement.executeUpdate();
			 
		}
		catch (SQLException e)
		{
			System.out.println("[연결 오류]\n" +  e.getStackTrace());
		}
		
		finally
		{
			closeDatabase();
		}
		
		return (result > 0) ? true : false;
	}	
	
	
	
	public static void main(String[] args)
	{
		//실행
		new MenuExam();

	}

	
	
	
	
	// *** 다이얼로그 클래스 ***
	/**
	 * 가입/수정 관련 이벤트를 포함해 JDialog를 생성하는 클래스 
	 * @author 안재하
	 */
	class MemberDialog
	{
		// *** 다이얼로그 UI 관련 변수 선언 ***
		
		JDialog jDialog = new JDialog(jFrame, "멤버 속성 입력");
		JButton idCheckButton = new JButton("ID 체크 확인");
		JButton submitButton = new JButton("가입");
		JButton cancelButton = new JButton("취소");
		
		JPanel fieldNamePanel = new JPanel(new GridLayout(4,1));
		JPanel fieldValuePanel = new JPanel(new GridLayout(4,1));
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JPanel idPanel = new JPanel(new BorderLayout());
		
		JLabel idLabel = new JLabel("ID");
		JLabel nameLabel = new JLabel("이름");
		JLabel ageLabel = new JLabel("나이");
		JLabel addrLabel = new JLabel("주소");
		
		JTextField idField = new JTextField(10);
		JTextField nameField = new JTextField(10);
		JTextField ageField = new JTextField(10);
		JTextField addrField = new JTextField(10);
		
		
		// *** 다이얼로그 관련 변수 선언 ***
		
		int dialogMode = NEW_MODE;

		
		
		/**
		 * [MemberDialog 생성자]
		 * 파라메터가 아무것도 없는 경우, 가입 모드로 인식한다.
		 * 다이얼로그 관련 모든 설정을 다 하지만, modal 설정을 위해
		 * modal 설정과 visible은 호출한 곳에서 처리한다.
		 */
		MemberDialog()
		{
			// *** 다이얼로그 UI 설정 ***
			
			//패널에 콤포넌트 추가
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
			

			// 다이얼로그에 패널 추가
			jDialog.add(fieldNamePanel, "West");
			jDialog.add(fieldValuePanel, "Center");
			jDialog.add(buttonPanel, "South");


			// 다이얼로그의 크기와 보기 설정
			jDialog.setSize(350, 250);
			jDialog.setLocationRelativeTo(jFrame);
			jDialog.setResizable(false);
			jDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
			// *** 이벤트 설정 ***
			
			//ID 체크 메뉴
			idCheckButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					//ID를 입력한 경우에만
					if(idField.getText().length() > 0)
					{
						//아이디가 존재하는 경우
						if(MenuExam.this.idCheck(idField.getText()))
						{
							JOptionPane.showMessageDialog(jDialog, "사용할 수 없는 아이디입니다!");
							
							idField.setText(null);
							idField.requestFocus();
						}
						//없는 아이디인 경우
						else
						{
							JOptionPane.showMessageDialog(jDialog, "사용 가능한 아이디입니다!");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(jDialog, "아이디를 입력해주세요!");
						idField.requestFocus();
					}
				}			
			});
			
			
			//가입(수정) 메뉴
			submitButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					//ID를 입력한 경우에만
					if(idField.getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(jDialog, "아이디를 입력해주세요!");
						idField.requestFocus();
					}
					else if(idField.getText().trim().length() > 30)
					{
						JOptionPane.showMessageDialog(jDialog, "아이디는 30자 이내로 입력해야 합니다!");
						idField.requestFocus();
					}
					else if(dialogMode == NEW_MODE && MenuExam.this.idCheck(idField.getText()))
					{
						JOptionPane.showMessageDialog(jDialog, "사용할 수 없는 아이디입니다!");
						
						idField.setText(null);
						idField.requestFocus();
					}
					else if(nameField.getText().trim().length() == 0 )
					{
						JOptionPane.showMessageDialog(jDialog, "이름을 입력해주세요!");
						nameField.requestFocus();
					}
					else if(nameField.getText().trim().length() > 20)
					{
						JOptionPane.showMessageDialog(jDialog, "이름 20자 이내로 입력해야 합니다!");
						nameField.requestFocus();
					}
					else if(ageField.getText().trim().length() == 0 )
					{
						JOptionPane.showMessageDialog(jDialog, "나이를 입력해주세요!");
						ageField.requestFocus();
					}
					else if(!isNumber(ageField.getText().trim()))
					{
						JOptionPane.showMessageDialog(jDialog, "나이를 숫자로 입력해주세요!");
						
						ageField.setText(null);
						ageField.requestFocus();
					}
					else if(Integer.valueOf(ageField.getText()) < 0 || Integer.valueOf(ageField.getText()) > 999)
					{
						JOptionPane.showMessageDialog(jDialog, "나이는 0부터 999사이의 값만 입력가능합니다.!");
						ageField.requestFocus();
					}
					else if(addrField.getText().trim().length() == 0 )
					{
						JOptionPane.showMessageDialog(jDialog, "주소를 입력해주세요!");
						addrField.requestFocus();
					}
					else if(addrField.getText().trim().length() > 100 )
					{
						JOptionPane.showMessageDialog(jDialog, "주소는 100자 이내로 입력해야 합니다!");
						addrField.requestFocus();
					}
					
					//오류가 없는 경우
					else
					{
						if(MenuExam.this.submitMember(dialogMode, idField.getText().trim(), nameField.getText().trim(), Integer.valueOf(ageField.getText()), addrField.getText().trim()))
						{
							if(dialogMode == NEW_MODE)
							{
								JOptionPane.showMessageDialog(jDialog, "가입을 축하드립니다!");
							}
							else
							{
								JOptionPane.showMessageDialog(jDialog, "정상적으로 수정되었습니다!");
							}
							
							jDialog.dispose(); //닫기
						}
						//없는 아이디인 경우
						else
						{
							JOptionPane.showMessageDialog(jDialog, "입력 과정에서 오류가 발생했습니다!");							
						}

						
					}
				}			
			});
			

			//취소 메뉴
			cancelButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					jDialog.dispose(); //닫기
				}			
			});
			
		}
		
		
		/**
		 * [MemberDialog 생성자]
		 * 파라메터가 있는 경우, 수정 모드로 인식한다.
		 * 파라메터가 없는 생성자를 호출해 해당 내용을 모두 실행하고
		 * 수정모드이므로 파라메터로 받아온 기존값을 필드에 출력해준다.
		 * 다이얼로그 관련 모든 설정을 다 하지만, modal 설정을 위해
		 * modal 설정과 visible은 호출한 곳에서 처리한다.
		 * @param id 아이디
		 * @param name 이름
		 * @param age 나이
		 * @param addr 주소
		 */
		MemberDialog(String id, String name, int age, String addr)
		{
			this();
			
			//모드 설정
			dialogMode = EDIT_MODE;
			
			//기존 값 설정
			idField.setText(id);
			nameField.setText(name);
			ageField.setText(String.valueOf(age));
			addrField.setText(addr);
			
			//ID 비활성화
			idField.setEnabled(false);
			idCheckButton.setEnabled(false);
			
			//버튼 수정
			submitButton.setText("수정");
		}
		
		
		
		/**
		 * [숫자인지 판별해주는 메소드]
		 * String값을 받아 Integer형으로 변환 테스트하여 오류 날 경우 false, 숫자일 경우 true를 리턴한다.
		 * @param s 임의의 문자열
		 * @return 숫자일경우 true, 아닐경우 false
		 */
		public boolean isNumber(String s)
		{
			
			try
			{
				//숫자로 바뀌는데 오류가 없으면 true
				Integer.valueOf(s);
				return true;
			}
			catch(NumberFormatException e)
			{
				//오류가 생기면 숫자가 아니므로 false
				return false;
			}
		}
	}
}
