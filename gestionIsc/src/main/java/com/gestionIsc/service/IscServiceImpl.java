package com.gestionIsc.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionIsc.model.Isc;
import com.gestionIsc.model.Project;
import com.gestionIsc.model.Ref_be;
import com.gestionIsc.repository.IscRepository;
import com.gestionIsc.repository.ProjectRepository;

@Service
public class IscServiceImpl implements IscService {
	@Autowired
	IscRepository iscRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Override
	public Isc saveIsc(Isc isc) {
		return iscRepository.save(isc);
	}

	@Override
	public List<Isc> findAll() {
		return iscRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		iscRepository.deleteById(id);
	}

	@Override
	public Optional<Isc> findById(Long id) {
		return iscRepository.findById(id);
	}

	@Override
	public void genererMonEtiquette(Long idIsc, Long idProject)
			throws URISyntaxException, IOException, InvalidFormatException {
		Project project = projectRepository.findById(idProject).get();
		Isc isc = iscRepository.findById(idIsc).get();
		Ref_be ref_be = new Ref_be(project.getNumAppelationProtegee(), project.getNumPcs(), project.getBonCommande(),
				isc.getClassification(), isc.getNumExemplaire(), isc.getNumCopie(),
				isc.getDateIsc(), null, isc.getTypeSupport(),null,null, project.getNameProject(), null);

		creerMyEtiquette(ref_be);

	}

	public static void creerMyEtiquette(Ref_be ref_be) throws InvalidFormatException, IOException {
		// create a document
		XWPFDocument doc = new XWPFDocument();

		XWPFParagraph dgami = doc.createParagraph();
		dgami.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun runDGA = dgami.createRun();
		runDGA.setBold(true);
		runDGA.setFontSize(20);
		runDGA.setText("DGA-MI");

		XWPFTable tableC = doc.createTable();
		tableC.getRow(0).getCell(0).removeParagraph(0);
		tableC.setTopBorder(XWPFBorderType.SINGLE, 30, 0, "FF0000");
		tableC.setBottomBorder(XWPFBorderType.SINGLE, 30, 0, "FF0000");
		tableC.setLeftBorder(XWPFBorderType.SINGLE, 30, 0, "FF0000");
		tableC.setRightBorder(XWPFBorderType.SINGLE, 30, 0, "FF0000");
		tableC.setWidth("45%");
		tableC.setTableAlignment(TableRowAlign.CENTER);
		XWPFTableRow tableRowOneC = tableC.getRow(0);
		XWPFTableCell cellC = tableRowOneC.getCell(0);
		XWPFParagraph classi = cellC.addParagraph();
		classi.setAlignment(ParagraphAlignment.CENTER);
		classi.setSpacingBefore(100);
		classi.setSpacingAfter(100);
		XWPFRun runC = classi.createRun();
		runC.setColor("FF0000");
		runC.setCapitalized(true);
		runC.setFontFamily("Arial");
		runC.setBold(true);
		runC.setFontSize(18);
		
		XWPFParagraph paragraph88 = doc.createParagraph();
		paragraph88.setAlignment(ParagraphAlignment.CENTER);
		paragraph88.setSpacingAfter(10);
		XWPFRun run88 = paragraph88.createRun();
		run88.setText("                         ");
		
		
		String classification = ref_be.getClassification();
		if (classification.equals("DR")) {
			runC.setText("Diffusion restreinte");
			tableC.setWidth("60%");
		}
		if (classification.equals("S")) {
			runC.setText("Secret");
		}
		if (classification.equals("DRSF")) {
			runC.setText("Diffusion restreinte");
			tableC.setWidth("60%");
			XWPFTable tableM = doc.createTable();
			tableM.getRow(0).getCell(0).removeParagraph(0);
			tableM.setTopBorder(XWPFBorderType.SINGLE, 25, 0, "0000FF");
			tableM.setBottomBorder(XWPFBorderType.SINGLE, 25, 0, "0000FF");
			tableM.setLeftBorder(XWPFBorderType.SINGLE, 25, 0, "0000FF");
			tableM.setRightBorder(XWPFBorderType.SINGLE, 25, 0, "0000FF");
			tableM.setWidth("45%");
			tableM.setTableAlignment(TableRowAlign.CENTER);
			XWPFTableRow tableRowOneM = tableM.getRow(0);
			XWPFTableCell cellM = tableRowOneM.getCell(0);
			XWPFParagraph mention = cellM.addParagraph();
			mention.setAlignment(ParagraphAlignment.CENTER);
			mention.setSpacingBefore(100);
			mention.setSpacingAfter(100);
			XWPFRun runM = mention.createRun();
			runM.setColor("0000FF");
			runM.setCapitalized(true);
			runM.setFontFamily("Arial");
			runM.setBold(true);
			runM.setFontSize(18);
			runM.setText("special france");
		}
		if (classification.equals("SSF")) {
			runC.setText("Secret");
			XWPFTable tableM = doc.createTable();
			tableM.getRow(0).getCell(0).removeParagraph(0);
			tableM.setTopBorder(XWPFBorderType.SINGLE, 25, 0, "0000FF");
			tableM.setBottomBorder(XWPFBorderType.SINGLE, 25, 0, "0000FF");
			tableM.setLeftBorder(XWPFBorderType.SINGLE, 25, 0, "0000FF");
			tableM.setRightBorder(XWPFBorderType.SINGLE, 25, 0, "0000FF");
			tableM.setWidth("45%");
			tableM.setTableAlignment(TableRowAlign.CENTER);
			XWPFTableRow tableRowOneM = tableM.getRow(0);
			XWPFTableCell cellM = tableRowOneM.getCell(0);
			XWPFParagraph mention = cellM.addParagraph();
			mention.setAlignment(ParagraphAlignment.CENTER);
			mention.setSpacingBefore(100);
			mention.setSpacingAfter(100);
			XWPFRun runM = mention.createRun();
			runM.setColor("0000FF");
			runM.setCapitalized(true);
			runM.setFontFamily("Arial");
			runM.setBold(true);
			runM.setFontSize(18);
			runM.setText("special france");
		}
		// runC.setText(ref_be.getClassification()); // classification de l'ISC :
		// exemple : classification = DR, écrire DIFFUSION
		// RESTREINTE 
//////////////////////////////////////////////////////////////////////////////////////////////////

		XWPFParagraph paragraph8 = doc.createParagraph();
		paragraph8.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run8 = paragraph8.createRun();
		run8.setText("                         ");

		XWPFTable table = doc.createTable();
		table.removeBorders();
		table.setTableAlignment(TableRowAlign.CENTER);
		table.getRow(0).getCell(0).removeParagraph(0);

		XWPFTableCell cell = table.getRow(0).getCell(0);
		XWPFTableCell cell2 = table.getRow(0).addNewTableCell();

		XWPFParagraph img = cell.addParagraph();
		img.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun runImg = img.createRun();
		String imgFile = "/home/vto/Downloads/KhiplusByAudensiel.jpeg";
		FileInputStream is = new FileInputStream(imgFile);
		runImg.addBreak();
		runImg.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(134), Units.toEMU(47)); // 200x200
																											// pixels
		XWPFParagraph date = cell2.addParagraph();
		date.setAlignment(ParagraphAlignment.CENTER);
		date.setVerticalAlignment(TextAlignment.CENTER);
		XWPFRun runD = date.createRun();
		//insertCurrentDateField(date);// inserer la date de génération de l'étiquette
		runD.setText("Le " + ref_be.getDateIsc());
//////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////

		XWPFParagraph reference = doc.createParagraph();
		reference.setAlignment(ParagraphAlignment.CENTER);
		reference.setSpacingBefore(400);
		XWPFRun runR = reference.createRun();
		runR.setCapitalized(true);
		runR.setText(ref_be.getNumAppelationProtegee() + "/" + ref_be.getNumPcs() + "/" + ref_be.getBonCommande() + '/'
				+ ref_be.getTypeSupport() + "/" + ref_be.getDateIsc() + "/" + ref_be.getNumExemplaire() + "/"
				+ ref_be.getNumCopie());
//////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////
		XWPFParagraph paragraph2 = doc.createParagraph();
		paragraph2.setSpacingBefore(800);
		paragraph2.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run2 = paragraph2.createRun();
		run2.setText("KHIPLUS S.A.S.");

		XWPFParagraph paragraph3 = doc.createParagraph();
		paragraph3.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run3 = paragraph3.createRun();
		run3.setText("66-72 rue Marceau");

		XWPFParagraph paragraph4 = doc.createParagraph();
		paragraph4.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run4 = paragraph4.createRun();
		run4.setText("93100 Montreuil");
//////////////////////////////////////////////////////////////////////////////////////////////////

		// write to a docx file
		FileOutputStream fo = null;
		try {
			// create .docx file
			fo = new FileOutputStream("/home/vto/Downloads/etiquette_"+ref_be.getNumAppelationProtegee() +'_'+ ref_be.getNumPcs()+'_'+ ref_be.getBonCommande()+'_'+ ref_be.getTypeSupport()+'_'+  ref_be.getDateIsc()+'_'+  ref_be.getNumExemplaire() +'_'+  ref_be.getNumCopie()+".docx");
//"/home/vto/Downloads/etiquette"+"ref_be.getNumAppelationProtegee()" +'/'+ "ref_be.getNumPcs()"+'/'+ "ref_be.getBonCommande()"+'/'+ "ref_be.getTypeSupport()"+'/'+  "ref_be.getDateIsc()"+'/'+  "ref_be.getNumExemplaire()"+'/'+  "ref_be.getNumCopie()"+".docx"
			// write to the .docx file
			doc.write(fo);
		} catch (IOException e) {
		} finally {
			if (fo != null) {
				try {
					fo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (doc != null) {
				try {
					doc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void insertCurrentDateField(XWPFParagraph paragraph) {
		XWPFRun run = paragraph.createRun();
		paragraph.getCTP().addNewFldSimple().setInstr("DATE \\@ \"dd MM yyyy\" \\* MERGEFORMAT");
	}

	/*
	 * @Override public void deleteById(Long id) { addressRepository.deleteById(id);
	 * 
	 * } public void updateAddress(Address address) {
	 * addressRepository.save(address); }
	 * 
	 * @Override public Optional<Address> findById(Long id) { return
	 * addressRepository.findById(id); }
	 */
}
