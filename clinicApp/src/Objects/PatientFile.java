package Objects;

public class PatientFile {

	private String FileName;
	private String FileDate;

	public PatientFile(String fileName, String fileDate) {
		FileName = fileName;
		FileDate = fileDate;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public String getFileDate() {
		return FileDate;
	}

	public void setFileDate(String fileDate) {
		FileDate = fileDate;
	}

	@Override
	public String toString() {
		return "PatientFile [FileName=" + FileName + ", FileDate=" + FileDate + "]";
	}

}
