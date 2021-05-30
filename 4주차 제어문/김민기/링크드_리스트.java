package datastruct;

public class MkLinkedList <T>{
	//노드 클래스
	private class Node{
		//왼쪽 오른쪽 링크 및 값
		Node leftLink;
		Node rightLink;
		T value;

		//기본생성자
		public Node() {
		}

		//모든 속성 생성자
		public Node(T value, Node leftLink, Node rightLink){
			this.value = value;
			this.leftLink = leftLink;
			this.rightLink = rightLink;
		}
	}

	//리스트의 크기
	private int size;
	//리스트의 헤드 ( 값을 저장하지 않음, 시작 주소)
	private final Node head;
	//리스트의 꼬리
	private Node tail;

	//기본 생성자
	public MkLinkedList(){
		this.head = new Node();
	}

	//헤드에 연결할 노드 추가하기
	public void addFirst(T val) {
		System.out.println("머리 추가 : " + val);
		//새 노드 갖기
		Node newNode = new Node(val, head, head.rightLink);
		//만약 리스트의 tail 이없다면, 즉 첫번째 노드라면
		if (head.rightLink == null) {
			//꼬리만 붙이기
			tail = newNode;
		}
		//아닐 경우
		else {
			//헤드의 오른쪽 노드의 왼쪽링크에 새 노드 연결하기
			head.rightLink.leftLink = newNode;
		}
		//헤드의 오른쪽 링크에 새 노드 연결하기
		head.rightLink = newNode;
		//사이즈 늘리기
		size++;
	}

	//꼬리에 추가하기
	public void addLast(T val){
		System.out.println("꼬리 추가 : " + val);
		//기존의 꼬리가 없다면 ( 첫 노드일 경우 )
		if (tail == null) {
			addFirst(val);
			return;
		}

		//꼬리를 왼쪽 링크로 갖는 새 노드 추가
		Node newNode = new Node(val, tail, null);
		//현재 꼬리 노드의 오른쪽 노드를 새 노드로 설정
		tail.rightLink = newNode;
		// 꼬리 노드를 현재 노드로 설정
		tail = newNode;
		size++;
	}

	//인덱스에 맞는 값 추가하기
	public void add(int index, T val) {
		System.out.println("허리에 추가 ! : " + val);
		//인덱스에 해당하는 노드 가져오기
		Node temp = getNode(index);
		//인덱스에 해당하는 노드가 없다면(범위 초과 등) 꼬리에 추가
		if (temp == null) {
			addLast(val);
			return;
		}
		//인덱스에 해당하는 노드의 왼쪽 링크를 새 노드의 왼쪽 링크로 연결, 오른쪽 링크를 인덱스에 해당하는 노드로 설정
		Node newNode = new Node(val, temp.leftLink, temp);
		//인덱스에 해당하는 노드의 왼쪽 노드의 오른쪽 링크를 현재 노드로 설정
		temp.leftLink.rightLink = newNode;
		//인덱스의 왼쪽 링크를 새 노드로 설정
		temp.leftLink = newNode;
		size++;
	}

	//인덱스에 해당하는 노드 가져오기
	private Node getNode(int index) {
		//범위 초과 및 음수 입력이면 null 반환
		if(index < 0 || index >= size)   return null;
		//첫번째 노드부터 인덱스만큼 이동
		Node temp = head.rightLink;
		for (int i = 0; i < index; i++) {
			temp = temp.rightLink;
		}
		//찾은 노드 반환
		return temp;
	}

	//인덱스에 해당하는 값 반환
	public T get(int index) {
		Node temp = this.getNode(index);
		//찾은 노드가 null 이라면 null, 아니라면 value 반환
		return temp == null ? null : temp.value;
	}

	//첫번째 노드 삭제
	public T removeFirst() {
		//노드가 하나도 없다면 반환
		if(head.rightLink == null)  return null;
		size--;

		//삭제할 노드 타겟
		Node target = head.rightLink;

		//헤드와 새로 연결해야할 노드 temp 설정
		Node temp = target.rightLink;
		//삭제할 노드의 왼쪽 링크를 끊음
		target.leftLink = null;

		//새로 연결할 노드가 없는 경우( 사이즈가 1)
		if (temp == null) {
			head.rightLink = null;
			// 그 경우 삭제할 노드가 tail 이므로 tail = null 설정
			tail = null;
			return target.value;
		}
		//삭제할 노드의 오른쪽 링크를 제거
		target.rightLink = null;
		//새로 연결할 노드의 left를 각각 헤드와 연결
		temp.leftLink = head;
		head.rightLink = temp;
		return target.value;
	}

	//꼬리 지우기
	public T removeLast() {
		//꼬리가 없다면, 즉 아무것도 없다면
		if(tail == null)    return null;
		size--;
		//꼬리 노드를 타겟으로 설정
		Node target = tail;
		//꼬리를 타겟의 왼쪽(현재 꼬리 이전 노드)으로 설정
		tail = target.leftLink;
		//타겟의 왼쪽 연결을 끊음
		target.leftLink = null;
		//새로운 꼬리의 오른쪽 연결을 끊음
		tail.rightLink = null;
		return target.value;
	}

	//인덱스 값에 맞는 노드 지우기
	public T remove(int index) {
		//인덱스 찾기
		Node target = this.getNode(index);
		//해당하는 인덱스가 없을 경우(범위초과 등) 리턴
		if (target == null)    return null;
		System.out.println("지우기 : " + index);
		size--;
		//꼬리가 아니라면 타겟을 지정하는 오른쪽 링크를 끊고, 타겟이전의 노드와 연결
		if (target != tail) {
			target.rightLink.leftLink = target.leftLink;
		} else {
			//꼬리라면, 꼬리 값 새로 지정
			tail = target.leftLink;
		}
		//타겟의 왼쪽 노드의 링크를 타겟의 오른쪽 노드로 설정
		target.leftLink.rightLink = target.rightLink;

		//타겟의 연결 끊기
		target.leftLink = null;
		target.rightLink = null;

		return target.value;
	}

	//스택 push
	public void push(T val) {
		this.addFirst(val);
	}
	//스택 pop
	public T pop() {
		return this.removeFirst();
	}

	//큐 peek
	public T peek() {
		return this.get(0);
	}

	//큐 poll
	public T poll() {
		return this.removeFirst();
	}

	//큐 offer
	public void offer(T val) {
		this.addLast(val);
	}

	//모든 노드 지우기
	public void clear() {
		while (size != 0) {
			removeLast();
		}
	}

	//사이즈 비우기
	public int size() {
		return size;
	}

	//노드가 비었는지 검사하기
	public boolean isEmpty() {
		return size == 0;
	}
}
