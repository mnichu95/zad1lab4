package zad1lab4;

class Message {

	public int id;
	public String text;
	public Priority priority;

	public Message(int id, String text, Priority priority) {
		this.id = id;
		this.text = text;
		this.priority = priority;

	}

	@Override
	public String toString() {
		return String.format("Message:%d, %s, %s \n", id, text, priority);

	}

}
