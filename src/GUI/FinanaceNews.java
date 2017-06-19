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
 * 뭐 때문에.....
 * 
 * @author www.zuidaima.com
 *
 */
public class FinanaceNews {// 탭 브라우저 기반 식

	private volatile String newUrl = null;// 최신 입력 링크
	private volatile boolean loadCompleted = false;// 현재 페이지 표시 완전히 가져오기
	private volatile boolean openNewItem = false;// 새 페이지 표시 새 창으로 열기

	/*
	 * 현재 탭 브라우저 사용하기
	 */

	private TabItem tabItem_now;// 현재 탭 항목
	private Browser browser_now;// 현재 기능 탐색기

	/*
	 * 브라우저 설정 매개 변수
	 */
	private String homePage;// 브라우저를 홈페이지
	private String address_front = "http://news.moneta.co.kr/Service/stock/ShellList.asp?LinkID=263&NewsSetID=1696&stockcode=";
	private String address_back = "&ModuleID=282";
	private String code;
			
	/*
	 * 브라우저 외형의 배치
	 */
	private Button button_back;// 뒤로 단추를 누르십시오
	private Button button_forward;// 앞으로 단추
	private Button button_go;// 앞으로 단추
	private Button button_stop;// 정지 버튼을
	private Combo combo_address;// 주소 표시줄
	private Browser browser_default = null;// 탐색 창

	private ProgressBar progressBar_status;// 페이지 열기 진행표 즉 페이지 가져오기 상황이 표시줄
	private Label label_status;// 마지막 페이지 열기 과정 보이기
	private TabFolder tabFolder;// Browser 용기
	private Composite composite_tool;// 도구 모음 구역
	private Composite composite_browser;// 탐색 창 구역
	private Composite composite_status;// 상태 표시줄 구역
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
		shell_default.setText("브라우저");
		GridLayout gl_shell = new GridLayout();
		gl_shell.marginWidth = 0;// 구성 요소 및 용기 가 수평 거리
		gl_shell.marginHeight = 0;// 구성 요소 및 용기 가장자리 수직 거리
		gl_shell.horizontalSpacing = 0;// 구성 요소 사이 수평 거리
		gl_shell.verticalSpacing = 0;// 구성 요소 사이의 수직 거리
		shell_default.setLayout(gl_shell);

		/*
		 * 웹 인터페이스 생성
		 */
		// createMenu();//실현되지 않았다
		createTool();
		createBrowser();
		createStatus();

		/*
		 * 브라우저 관련 사건 들을 수 없습니다.
		 */
		runThread();
	}

	/*
	 * 기본 만들기 도구 모음 안 포함한 관련 사건 감청
	 */
	private void createTool() {

		composite_tool = new Composite(shell_default, SWT.BORDER);
		// GridData()첫 번째 매개 변수, 수평 방식, 두 번째 매개 변수 는 수직 정렬 방식, 세 번째는 수준 선점 여부, 네
		// 번째 인자 수직이다 선점 여부
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_composite.heightHint = 30;// 고도, 폭
		gd_composite.widthHint = 549;
		composite_tool.setLayoutData(gd_composite);
		GridLayout fl_composite = new GridLayout();
		fl_composite.numColumns = 8;
		composite_tool.setLayout(fl_composite);

		button_back = new Button(composite_tool, SWT.NONE);
		button_back.setLayoutData(new GridData(27, SWT.DEFAULT));// 설정 크기 조정 및
																	// 형식
		button_back.setText("<");

		button_forward = new Button(composite_tool, SWT.NONE);
		button_forward.setLayoutData(new GridData(24, SWT.DEFAULT));
		button_forward.setText(">");

		combo_address = new Combo(composite_tool, SWT.BORDER);
		final GridData gd_combo_3 = new GridData(SWT.FILL, SWT.LEFT, true, false);// 창
																					// 변화가
																					// 있을
																					// 때
																					// 자동
																					// 확장
																					// 수평
																					// 방향의
																					// 크기
		gd_combo_3.widthHint = 300;// 시작 너비
		gd_combo_3.minimumWidth = 50;// 설정 최소 폭
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
	 * 생성 브라우저 등 관련 사건 감청 않는다.
	 */
	private void createBrowser() {
		composite_browser = new Composite(shell_default, SWT.NONE);
		final GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true);// 가득
																					// 창
																					// 뿐만
																					// 아니라
																					// 수평과
																					// 수직으로
																					// 방향을
																					// 따라
																					// 창
																					// 때문에
																					// 변하다
		gd_composite.heightHint = 273;
		composite_browser.setLayoutData(gd_composite);
		GridLayout gl_composite = new GridLayout();
		gl_composite.marginHeight = 0;// 으로 구성 요소 상하 방향 용기
		gl_composite.marginWidth = 0;// 으로 구성 요소 정도 방향 가득 용기
		composite_browser.setLayout(gl_composite);

		tabFolder = new TabFolder(composite_browser, SWT.NONE);
		final GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_tabFolder.heightHint = 312;
		gd_tabFolder.widthHint = 585;
		tabFolder.setLayoutData(gd_tabFolder);

		/*
		 * 태그 추가하기 위해 오른쪽 버튼 기능
		 */
		tabFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (e.button == 3) {// 오른쪽 단추
					Menu menu_itemRightMouse = new Menu(shell_default, SWT.POP_UP);
					tabFolder.setMenu(menu_itemRightMouse);
					MenuItem menuItem_itemClose = new MenuItem(menu_itemRightMouse, SWT.NONE);
					menuItem_itemClose.setText("현재 탭 닫기");
					menuItem_itemClose.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							if (tabFolder.getItemCount() != 1) {// 아니, 단지 한 탭
																// 상황에서
								browser_now.dispose();
								tabItem_now.dispose();
								tabFolder.redraw();
							} else {// 오직 하나 탭
								browser_now.setUrl(":blank");
								browser_now.setText("");
							}
						}
					});
					MenuItem menuItem_itemCloseAll = new MenuItem(menu_itemRightMouse, SWT.NONE);
					menuItem_itemCloseAll.setText("모든 탭 닫기");
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
		browser_default.setUrl(homePage);// 브라우저 메 인 화면 표시

		/*
		 * 그 초기화 탭 잡담 끝. 선택하십시오
		 */
		tabFolder.setSelection(tabItem_default);

	}

	/*
	 * 상태 표시줄 만들기 브라우저 하단에 안 포함한 관련 사건 감청
	 */
	private void createStatus() {
		composite_status = new Composite(shell_default, SWT.NONE);
		final GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, false);// 매개
																					// 변수
																					// true
																					// 때문에
																					// 상태
																					// 표시줄
																					// 자동으로
																					// 수준
																					// 신축
																					// 수
																					// 있다
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
		progressBar_status.setVisible(false);// 열 과정 시작 안 보인다

	}

	private void runThread() {

		/*
		 * 새 탭 브라우저 전진 · 뒤로 단추를 누르면 기본 접근성 위해 사용할 수 없습니다.
		 */
		button_back.setEnabled(false);
		button_forward.setEnabled(false);

		/*
		 * 현재 탭 브라우저 가져오기 및 기능 Browser
		 */
		tabItem_now = tabFolder.getItem(tabFolder.getSelectionIndex());
		browser_now = (Browser) tabItem_now.getControl();

		/*
		 * 선택한 사건 때 현재 탐색기 탭 수정
		 */
		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TabItem temp = (TabItem) e.item;
				if (temp != tabItem_now) {// 재선거 한 탭 방지 예방 여러 차례 트리거 같은 사건
					tabItem_now = temp;
					browser_now = (Browser) tabItem_now.getControl();
					// System.out.println("현재 탭 변경된 '); // 디버그 문

					/*
					 * 지금 상응하는 탭으로, 전진하다, 뒤로 단추를 접근성 달라요.
					 */
					if (browser_now.isBackEnabled()) {// 뒤로 단추를 접근성
						button_back.setEnabled(true);
					} else {
						button_back.setEnabled(false);
					}
					if (browser_now.isForwardEnabled()) {// 앞으로 단추를 접근성
						button_forward.setEnabled(true);
					} else {
						button_forward.setEnabled(false);
					}

				}
			}
		});

		/*
		 * 추가 브라우저 뒤로, 앞으로, 전진하다, 정지 버튼을 사건 감청
		 */
		button_back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (browser_now.isBackEnabled()) {// 이번 정말 뒤로
					browser_now.back();
					button_forward.setEnabled(true);// 다음부터는 나아가다, 전진하다 단추를 사용할
													// 수 없습니다.
					// System.out.println("근데 뒤로 '); // 디버그 문
				}
				if (!browser_now.isBackEnabled()) {// 나중에 안 된다 뒤로, 뒤로 단추를 사용할 수
													// 없습니다.
					button_back.setEnabled(false);
				}
			}
		});

		button_forward.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (browser_now.isForwardEnabled()) {// 이번 정말 전진하다
					browser_now.forward();
					button_back.setEnabled(true);// 뒤로 단추를 사용할 수 없습니다.
					// System.out.println("근데 앞으로 '); // 디버그 문
				}
				if (!browser_now.isForwardEnabled()) {// 나중에 안 된다. 앞으로 나아가다,
														// 전진하다 단추를 사용할 수 없습니다.
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

		combo_address.addKeyListener(new KeyAdapter() {// 수동 입력 주소 표시줄에 후 Enter
														// 키를 누르면 상응하는 사이트 가기
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR) {// 리턴 키 트리거 이벤트
					browser_now.setUrl(combo_address.getText());
				}
			}
		});

		/*
		 * 1>지금 addOpenWindowListener () 아래 open () 쓰기 e.browser = browser_new
		 * 상황에서 책갈피를 새 하이퍼링크, 오직 이 페이지에 링크를 누르면 또한 링크 안 새로운 페이지를 열 때 벌어진. 2>지금
		 * addOpenWindowListener () 아래 open () 쓰기 e.browser = browser_new 상황에서
		 * 책갈피를 새 하이퍼링크, 오직 이 페이지에 링크를 누르면 또한 링크 새로운 페이지를 열 때 벌어진. 이상 두 가지 말고 바깥
		 * 당연히 아직 browser.back (포함), browser.forward (), browser.go (),
		 * browser.setUrl () 발생 시 발동, 하지만 changing () 는 쓰기 e.browser =
		 * browser_new 상황에서 지원되지 browser.setUrl () 트리거
		 */
		browser_now.addLocationListener(new LocationAdapter() {
			@Override
			public void changing(LocationEvent e) {// 하이퍼링크 주소 바뀐 말했다
				if (openNewItem == false) {// 새 페이지 같은 탭으로 열기
					button_back.setEnabled(true);// 뒤로 단추를 사용할 수 있습니다. 이 구절은 뒤로
													// 단추를 사용할 수 있는 논리 시작 시 결정
				}
				// System.out.println("location_changing");// 디버그 문
			}

			@Override
			public void changed(LocationEvent e) {// 링크 주소 찾았다 페이지
				combo_address.setText(e.location);// 링크 주소 변경 보이기
				/*
				 * 새 페이지 이미 열어서 browser 있는 LocationListener 이미 감청 완료,
				 * openNewItem 답장 기본값
				 */
				if (openNewItem == true) {
					openNewItem = false;
				}
				// System.out.println("location_changed");// 디버그 문

			}

		});

		/*
		 * 새로운 하이퍼링크 페이지 가져오기%, 책갈피를 새 페이지 발생, 이때 이미 링크 주소
		 */
		browser_now.addProgressListener(new ProgressAdapter() {
			@Override
			public void changed(ProgressEvent e) {// 이번 사건이 끊임없이 일어난 는 페이지 가져오기
													// 과정에서
				progressBar_status.setMaximum(e.total);// e.total 에서 가장 시작 페이지
														// 끝까지 페이지 의 수치
				progressBar_status.setSelection(e.current);
				if (e.current != e.total) {// 페이지 아직 완전히 가져오기
					loadCompleted = false;
					progressBar_status.setVisible(true);// 페이지 가져오기 상황이 표시줄을 보이는
				} else {
					loadCompleted = true;
					progressBar_status.setVisible(false);// 페이지 가져오기 상황이 표시줄 안
															// 보인다
				}
				// System.out.println("progress_changed");//디버그 문

			}

			@Override
			public void completed(ProgressEvent arg0) {// 발생 한 번 가져오기 페이지 때 본
														// 모니터 changed 사건 발생 전에
														// 마지막으로
				// System.out.println("progress_completed");//디버그 문
			}
		});

		/*
		 * 페이지 내용 가져오는 addProgressListener 과정, 문자 보이기 () 과정 을 동시에 인지되었습니다 페이지 수
		 * 있는 슈퍼 링크 열기 이미 바로 간 기능 온 가져오기 새 링크 주소
		 */
		browser_now.addStatusTextListener(new StatusTextListener() {
			public void changed(StatusTextEvent e) {
				if (loadCompleted == false) {
					label_status.setText(e.text);
				} else {
					newUrl = e.text;// 페이지 가져오기 완료, 포착 페이지에 링크를 열 수 있다
				}
				// System.out.println("statusText_changed");//디버그 문
			}
		});

		/*
		 * 페이지 표시 문구 표시, 새로운 페이지 가져오기 발생
		 */
		browser_now.addTitleListener(new TitleListener() {
			public void changed(TitleEvent e) {
				shell_default.setText(e.title);
				if (e.title.length() > 3) {// 현재 페이지의 팁 표시 문자 탭에
					tabItem_now.setText(e.title.substring(0, 3) + "..");
				} else {
					tabItem_now.setText(e.title);
				}
				tabItem_now.setToolTipText(e.title);// 탭 표시 프롬프트
			}
		});

		/*
		 * 새 페이지 열기 현재 페이지 열기 새 링크 필요한 새 창 페이지 열기 발생.addOpenWindowListener 다음
		 * open () 중 한 마디 e.browser = browser_new;중요한 부분. 연락
		 * addOpenWindowListener, addVisibilityWindowListener 과
		 * addDisposeListener 값 배달 중추.
		 */
		browser_now.addOpenWindowListener(new OpenWindowListener() {// 현재 페이지의
																	// 링크 페이지 열기
																	// 클릭
			public void open(WindowEvent e) {
				Browser browser_new = new Browser(tabFolder, SWT.NONE);
				TabItem tabItem_new = new TabItem(tabFolder, SWT.NONE);
				tabItem_new.setControl(browser_new);
				tabFolder.setSelection(tabItem_new);// 새 탭 열기 페이지 잡담 끝.
				tabFolder.redraw();// 새로 고침 용기
				browser_new.setUrl(newUrl);// 새 탭으로 링크 설정 새로운 주소
				openNewItem = true;// 새 페이지 새 탭을 엽니다

				/*
				 * 관건이 되는 부분 알려 by browser_new 새로운 페이지 열기 하면 실현 이 말 안 팝업 운영 체제
				 * 기본 브라우저 되었다
				 */
				e.browser = browser_new;
				// System.out.println("OpenWindowListener_open");//디버그 문

				/*
				 * 새 탭 브라우저 추가합니다 사건 감청
				 */
				display.syncExec(new Runnable() {
					public void run() {
						runThread();
					}
				});

			}
		});

		/*
		 * 브라우저 닫기 이벤트가 현재 기능 탐색기, 닫기, 아니면 브라우저 주 창 닫기, 실행, 그리고 프로세스
		 */
		browser_now.addCloseWindowListener(new CloseWindowListener() {
			public void close(WindowEvent e) {
				browser_now.dispose();
			}
		});

	}
}
