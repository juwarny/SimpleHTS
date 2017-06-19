package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.CloseWindowListener;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.browser.TitleEvent;
import org.eclipse.swt.browser.TitleListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * �� ������.....
 * 
 * @author www.zuidaima.com
 *
 */
public class FinanaceNews {// �� ������ ��� ��

	private volatile String newUrl = null;// �ֽ� �Է� ��ũ
	private volatile boolean loadCompleted = false;// ���� ������ ǥ�� ������ ��������
	private volatile boolean openNewItem = false;// �� ������ ǥ�� �� â���� ����

	/*
	 * ���� �� ������ ����ϱ�
	 */

	private TabItem tabItem_now;// ���� �� �׸�
	private Browser browser_now;// ���� ��� Ž����

	/*
	 * ������ ���� �Ű� ����
	 */
	private String homePage;// �������� Ȩ������
	private String address_front = "http://news.moneta.co.kr/Service/stock/ShellList.asp?LinkID=263&NewsSetID=1696&stockcode=";
	private String address_back = "&ModuleID=282";
	private String code;
			
	/*
	 * ������ ������ ��ġ
	 */
	private Button button_back;// �ڷ� ���߸� �����ʽÿ�
	private Button button_forward;// ������ ����
	private Button button_go;// ������ ����
	private Button button_stop;// ���� ��ư��
	private Combo combo_address;// �ּ� ǥ����
	private Browser browser_default = null;// Ž�� â

	private ProgressBar progressBar_status;// ������ ���� ����ǥ �� ������ �������� ��Ȳ�� ǥ����
	private Label label_status;// ������ ������ ���� ���� ���̱�
	private TabFolder tabFolder;// Browser ���
	private Composite composite_tool;// ���� ���� ����
	private Composite composite_browser;// Ž�� â ����
	private Composite composite_status;// ���� ǥ���� ����
	protected Display display;
	protected Shell shell_default;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		try {
			FinanaceNews window = new FinanaceNews("A003540");
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public FinanaceNews(String itemcode){
		code = itemcode.substring(1, itemcode.length());
	}
	public void setHomePage(String code){
		homePage = address_front+code+address_back;
	}
	
	/**
	 * Open the window
	 */
	public void open() {
		display = Display.getDefault();
		shell_default = new Shell(display);
		createContents();

		shell_default.open();
		shell_default.layout();
		while (!shell_default.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		shell_default.setSize(1270, 720);
		shell_default.setText("������");
		GridLayout gl_shell = new GridLayout();
		gl_shell.marginWidth = 0;// ���� ��� �� ��� �� ���� �Ÿ�
		gl_shell.marginHeight = 0;// ���� ��� �� ��� �����ڸ� ���� �Ÿ�
		gl_shell.horizontalSpacing = 0;// ���� ��� ���� ���� �Ÿ�
		gl_shell.verticalSpacing = 0;// ���� ��� ������ ���� �Ÿ�
		shell_default.setLayout(gl_shell);

		/*
		 * �� �������̽� ����
		 */
		// createMenu();//�������� �ʾҴ�
		createTool();
		createBrowser();
		createStatus();

		/*
		 * ������ ���� ��� ���� �� �����ϴ�.
		 */
		runThread();
	}

	/*
	 * �⺻ ����� ���� ���� �� ������ ���� ��� ��û
	 */
	private void createTool() {

		composite_tool = new Composite(shell_default, SWT.BORDER);
		// GridData()ù ��° �Ű� ����, ���� ���, �� ��° �Ű� ���� �� ���� ���� ���, �� ��°�� ���� ���� ����, ��
		// ��° ���� �����̴� ���� ����
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_composite.heightHint = 30;// ��, ��
		gd_composite.widthHint = 549;
		composite_tool.setLayoutData(gd_composite);
		GridLayout fl_composite = new GridLayout();
		fl_composite.numColumns = 8;
		composite_tool.setLayout(fl_composite);

		button_back = new Button(composite_tool, SWT.NONE);
		button_back.setLayoutData(new GridData(27, SWT.DEFAULT));// ���� ũ�� ���� ��
																	// ����
		button_back.setText("<");

		button_forward = new Button(composite_tool, SWT.NONE);
		button_forward.setLayoutData(new GridData(24, SWT.DEFAULT));
		button_forward.setText(">");

		combo_address = new Combo(composite_tool, SWT.BORDER);
		final GridData gd_combo_3 = new GridData(SWT.FILL, SWT.LEFT, true, false);// â
																					// ��ȭ��
																					// ����
																					// ��
																					// �ڵ�
																					// Ȯ��
																					// ����
																					// ������
																					// ũ��
		gd_combo_3.widthHint = 300;// ���� �ʺ�
		gd_combo_3.minimumWidth = 50;// ���� �ּ� ��
		combo_address.setLayoutData(gd_combo_3);

		button_go = new Button(composite_tool, SWT.NONE);
		button_go.setLayoutData(new GridData(25, SWT.DEFAULT));
		button_go.setText("go");

		button_stop = new Button(composite_tool, SWT.NONE);
		button_stop.setLayoutData(new GridData(24, SWT.DEFAULT));
		button_stop.setText("stop");

		final Label label = new Label(composite_tool, SWT.SEPARATOR | SWT.VERTICAL);
		label.setLayoutData(new GridData(2, 17));

	}

	/*
	 * ���� ������ �� ���� ��� ��û �ʴ´�.
	 */
	private void createBrowser() {
		composite_browser = new Composite(shell_default, SWT.NONE);
		final GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true);// ����
																					// â
																					// �Ӹ�
																					// �ƴ϶�
																					// �����
																					// ��������
																					// ������
																					// ����
																					// â
																					// ������
																					// ���ϴ�
		gd_composite.heightHint = 273;
		composite_browser.setLayoutData(gd_composite);
		GridLayout gl_composite = new GridLayout();
		gl_composite.marginHeight = 0;// ���� ���� ��� ���� ���� ���
		gl_composite.marginWidth = 0;// ���� ���� ��� ���� ���� ���� ���
		composite_browser.setLayout(gl_composite);

		tabFolder = new TabFolder(composite_browser, SWT.NONE);
		final GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_tabFolder.heightHint = 312;
		gd_tabFolder.widthHint = 585;
		tabFolder.setLayoutData(gd_tabFolder);

		/*
		 * �±� �߰��ϱ� ���� ������ ��ư ���
		 */
		tabFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (e.button == 3) {// ������ ����
					Menu menu_itemRightMouse = new Menu(shell_default, SWT.POP_UP);
					tabFolder.setMenu(menu_itemRightMouse);
					MenuItem menuItem_itemClose = new MenuItem(menu_itemRightMouse, SWT.NONE);
					menuItem_itemClose.setText("���� �� �ݱ�");
					menuItem_itemClose.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							if (tabFolder.getItemCount() != 1) {// �ƴ�, ���� �� ��
																// ��Ȳ����
								browser_now.dispose();
								tabItem_now.dispose();
								tabFolder.redraw();
							} else {// ���� �ϳ� ��
								browser_now.setUrl(":blank");
								browser_now.setText("");
							}
						}
					});
					MenuItem menuItem_itemCloseAll = new MenuItem(menu_itemRightMouse, SWT.NONE);
					menuItem_itemCloseAll.setText("��� �� �ݱ�");
					menuItem_itemCloseAll.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							shell_default.close();
						}
					});
				}
			}
		});

		final TabItem tabItem_default = new TabItem(tabFolder, SWT.NONE);
		browser_default = new Browser(tabFolder, SWT.NONE);
		tabItem_default.setControl(browser_default);
		setHomePage(code);
		browser_default.setUrl(homePage);// ������ �� �� ȭ�� ǥ��

		/*
		 * �� �ʱ�ȭ �� ��� ��. �����Ͻʽÿ�
		 */
		tabFolder.setSelection(tabItem_default);

	}

	/*
	 * ���� ǥ���� ����� ������ �ϴܿ� �� ������ ���� ��� ��û
	 */
	private void createStatus() {
		composite_status = new Composite(shell_default, SWT.NONE);
		final GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, false);// �Ű�
																					// ����
																					// true
																					// ������
																					// ����
																					// ǥ����
																					// �ڵ�����
																					// ����
																					// ����
																					// ��
																					// �ִ�
		gd_composite.heightHint = 20;
		gd_composite.widthHint = 367;
		composite_status.setLayoutData(gd_composite);
		GridLayout gl_composite = new GridLayout();
		gl_composite.numColumns = 2;
		gl_composite.marginBottom = 5;
		composite_status.setLayout(gl_composite);

		label_status = new Label(composite_status, SWT.NONE);
		GridData gd_status = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_status.heightHint = 13;
		gd_status.widthHint = 525;
		label_status.setLayoutData(gd_status);

		progressBar_status = new ProgressBar(composite_status, SWT.BORDER | SWT.SMOOTH);
		progressBar_status.setLayoutData(new GridData(80, 12));
		progressBar_status.setVisible(false);// �� ���� ���� �� ���δ�

	}

	private void runThread() {

		/*
		 * �� �� ������ ���� �� �ڷ� ���߸� ������ �⺻ ���ټ� ���� ����� �� �����ϴ�.
		 */
		button_back.setEnabled(false);
		button_forward.setEnabled(false);

		/*
		 * ���� �� ������ �������� �� ��� Browser
		 */
		tabItem_now = tabFolder.getItem(tabFolder.getSelectionIndex());
		browser_now = (Browser) tabItem_now.getControl();

		/*
		 * ������ ��� �� ���� Ž���� �� ����
		 */
		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TabItem temp = (TabItem) e.item;
				if (temp != tabItem_now) {// �缱�� �� �� ���� ���� ���� ���� Ʈ���� ���� ���
					tabItem_now = temp;
					browser_now = (Browser) tabItem_now.getControl();
					// System.out.println("���� �� ����� '); // ����� ��

					/*
					 * ���� �����ϴ� ������, �����ϴ�, �ڷ� ���߸� ���ټ� �޶��.
					 */
					if (browser_now.isBackEnabled()) {// �ڷ� ���߸� ���ټ�
						button_back.setEnabled(true);
					} else {
						button_back.setEnabled(false);
					}
					if (browser_now.isForwardEnabled()) {// ������ ���߸� ���ټ�
						button_forward.setEnabled(true);
					} else {
						button_forward.setEnabled(false);
					}

				}
			}
		});

		/*
		 * �߰� ������ �ڷ�, ������, �����ϴ�, ���� ��ư�� ��� ��û
		 */
		button_back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (browser_now.isBackEnabled()) {// �̹� ���� �ڷ�
					browser_now.back();
					button_forward.setEnabled(true);// �������ʹ� ���ư���, �����ϴ� ���߸� �����
													// �� �����ϴ�.
					// System.out.println("�ٵ� �ڷ� '); // ����� ��
				}
				if (!browser_now.isBackEnabled()) {// ���߿� �� �ȴ� �ڷ�, �ڷ� ���߸� ����� ��
													// �����ϴ�.
					button_back.setEnabled(false);
				}
			}
		});

		button_forward.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (browser_now.isForwardEnabled()) {// �̹� ���� �����ϴ�
					browser_now.forward();
					button_back.setEnabled(true);// �ڷ� ���߸� ����� �� �����ϴ�.
					// System.out.println("�ٵ� ������ '); // ����� ��
				}
				if (!browser_now.isForwardEnabled()) {// ���߿� �� �ȴ�. ������ ���ư���,
														// �����ϴ� ���߸� ����� �� �����ϴ�.
					button_forward.setEnabled(false);
				}
			}
		});

		button_stop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				browser_now.stop();
			}
		});

		combo_address.addKeyListener(new KeyAdapter() {// ���� �Է� �ּ� ǥ���ٿ� �� Enter
														// Ű�� ������ �����ϴ� ����Ʈ ����
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR) {// ���� Ű Ʈ���� �̺�Ʈ
					browser_now.setUrl(combo_address.getText());
				}
			}
		});

		/*
		 * 1>���� addOpenWindowListener () �Ʒ� open () ���� e.browser = browser_new
		 * ��Ȳ���� å���Ǹ� �� �����۸�ũ, ���� �� �������� ��ũ�� ������ ���� ��ũ �� ���ο� �������� �� �� ������. 2>����
		 * addOpenWindowListener () �Ʒ� open () ���� e.browser = browser_new ��Ȳ����
		 * å���Ǹ� �� �����۸�ũ, ���� �� �������� ��ũ�� ������ ���� ��ũ ���ο� �������� �� �� ������. �̻� �� ���� ���� �ٱ�
		 * �翬�� ���� browser.back (����), browser.forward (), browser.go (),
		 * browser.setUrl () �߻� �� �ߵ�, ������ changing () �� ���� e.browser =
		 * browser_new ��Ȳ���� �������� browser.setUrl () Ʈ����
		 */
		browser_now.addLocationListener(new LocationAdapter() {
			@Override
			public void changing(LocationEvent e) {// �����۸�ũ �ּ� �ٲ� ���ߴ�
				if (openNewItem == false) {// �� ������ ���� ������ ����
					button_back.setEnabled(true);// �ڷ� ���߸� ����� �� �ֽ��ϴ�. �� ������ �ڷ�
													// ���߸� ����� �� �ִ� �� ���� �� ����
				}
				// System.out.println("location_changing");// ����� ��
			}

			@Override
			public void changed(LocationEvent e) {// ��ũ �ּ� ã�Ҵ� ������
				combo_address.setText(e.location);// ��ũ �ּ� ���� ���̱�
				/*
				 * �� ������ �̹� ��� browser �ִ� LocationListener �̹� ��û �Ϸ�,
				 * openNewItem ���� �⺻��
				 */
				if (openNewItem == true) {
					openNewItem = false;
				}
				// System.out.println("location_changed");// ����� ��

			}

		});

		/*
		 * ���ο� �����۸�ũ ������ ��������%, å���Ǹ� �� ������ �߻�, �̶� �̹� ��ũ �ּ�
		 */
		browser_now.addProgressListener(new ProgressAdapter() {
			@Override
			public void changed(ProgressEvent e) {// �̹� ����� ���Ӿ��� �Ͼ �� ������ ��������
													// ��������
				progressBar_status.setMaximum(e.total);// e.total ���� ���� ���� ������
														// ������ ������ �� ��ġ
				progressBar_status.setSelection(e.current);
				if (e.current != e.total) {// ������ ���� ������ ��������
					loadCompleted = false;
					progressBar_status.setVisible(true);// ������ �������� ��Ȳ�� ǥ������ ���̴�
				} else {
					loadCompleted = true;
					progressBar_status.setVisible(false);// ������ �������� ��Ȳ�� ǥ���� ��
															// ���δ�
				}
				// System.out.println("progress_changed");//����� ��

			}

			@Override
			public void completed(ProgressEvent arg0) {// �߻� �� �� �������� ������ �� ��
														// ����� changed ��� �߻� ����
														// ����������
				// System.out.println("progress_completed");//����� ��
			}
		});

		/*
		 * ������ ���� �������� addProgressListener ����, ���� ���̱� () ���� �� ���ÿ� �����Ǿ����ϴ� ������ ��
		 * �ִ� ���� ��ũ ���� �̹� �ٷ� �� ��� �� �������� �� ��ũ �ּ�
		 */
		browser_now.addStatusTextListener(new StatusTextListener() {
			public void changed(StatusTextEvent e) {
				if (loadCompleted == false) {
					label_status.setText(e.text);
				} else {
					newUrl = e.text;// ������ �������� �Ϸ�, ���� �������� ��ũ�� �� �� �ִ�
				}
				// System.out.println("statusText_changed");//����� ��
			}
		});

		/*
		 * ������ ǥ�� ���� ǥ��, ���ο� ������ �������� �߻�
		 */
		browser_now.addTitleListener(new TitleListener() {
			public void changed(TitleEvent e) {
				shell_default.setText(e.title);
				if (e.title.length() > 3) {// ���� �������� �� ǥ�� ���� �ǿ�
					tabItem_now.setText(e.title.substring(0, 3) + "..");
				} else {
					tabItem_now.setText(e.title);
				}
				tabItem_now.setToolTipText(e.title);// �� ǥ�� ������Ʈ
			}
		});

		/*
		 * �� ������ ���� ���� ������ ���� �� ��ũ �ʿ��� �� â ������ ���� �߻�.addOpenWindowListener ����
		 * open () �� �� ���� e.browser = browser_new;�߿��� �κ�. ����
		 * addOpenWindowListener, addVisibilityWindowListener ��
		 * addDisposeListener �� ��� ����.
		 */
		browser_now.addOpenWindowListener(new OpenWindowListener() {// ���� ��������
																	// ��ũ ������ ����
																	// Ŭ��
			public void open(WindowEvent e) {
				Browser browser_new = new Browser(tabFolder, SWT.NONE);
				TabItem tabItem_new = new TabItem(tabFolder, SWT.NONE);
				tabItem_new.setControl(browser_new);
				tabFolder.setSelection(tabItem_new);// �� �� ���� ������ ��� ��.
				tabFolder.redraw();// ���� ��ħ ���
				browser_new.setUrl(newUrl);// �� ������ ��ũ ���� ���ο� �ּ�
				openNewItem = true;// �� ������ �� ���� ���ϴ�

				/*
				 * ������ �Ǵ� �κ� �˷� by browser_new ���ο� ������ ���� �ϸ� ���� �� �� �� �˾� � ü��
				 * �⺻ ������ �Ǿ���
				 */
				e.browser = browser_new;
				// System.out.println("OpenWindowListener_open");//����� ��

				/*
				 * �� �� ������ �߰��մϴ� ��� ��û
				 */
				display.syncExec(new Runnable() {
					public void run() {
						runThread();
					}
				});

			}
		});

		/*
		 * ������ �ݱ� �̺�Ʈ�� ���� ��� Ž����, �ݱ�, �ƴϸ� ������ �� â �ݱ�, ����, �׸��� ���μ���
		 */
		browser_now.addCloseWindowListener(new CloseWindowListener() {
			public void close(WindowEvent e) {
				browser_now.dispose();
			}
		});

	}
}
