package com.gestionIsc.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionIsc.model.Address;
import com.gestionIsc.model.Isc;
import com.gestionIsc.model.Project;
import com.gestionIsc.model.Ref_be;
import com.gestionIsc.repository.AddressRepository;
import com.gestionIsc.repository.BeRepository;
import com.gestionIsc.repository.IscRepository;
import com.gestionIsc.repository.ProjectRepository;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	IscRepository iscRepository;
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	BeRepository beRepository;

	@Override
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		addressRepository.deleteById(id);

	}

	public void updateAddress(Address address) {
		addressRepository.save(address);
	}

	@Override
	public Optional<Address> findById(Long id) {
		return addressRepository.findById(id);
	}
	
	@Override
	public void genererMonBordereau(Long idProject, Long idDestinataire, List<Long> idIscs) {
		List<Ref_be> ref_bes = new ArrayList<Ref_be>();
		Address address = addressRepository.findById(idDestinataire).get(); 
		Project project = projectRepository.findById(idProject).get(); 
		List<Isc> iscs = new ArrayList<>();
		for (Long indice : idIscs) {
			iscs.add(iscRepository.findById(indice).get());
			}
		for (Isc isc : iscs) {
			ref_bes
			.add(new Ref_be(project.getNumAppelationProtegee(),project.getNumPcs(),
					project.getBonCommande(), isc.getClassification(),
					isc.getNumExemplaire(), isc.getNumCopie(), isc.getDateIsc(),
					address.getNom(), isc.getTypeSupport(), null, null, project.getNameProject(),
					address.getOrganisme())); }
		createWordDocument(ref_bes);
		}

	/*
	 * @Override public void genererMonBordereau(Long idProject, Long
	 * idDestinataire, List<Long>idIscs) { List<Ref_be> ref_bes = new
	 * ArrayList<Ref_be>(); Address address =
	 * addressRepository.findById(idDestinataire).get(); Project project =
	 * projectRepository.findById(idProject).get(); List<Isc> iscs = new
	 * ArrayList<>(); for (Long indice : idIscs) {
	 * iscs.add(iscRepository.findById(indice).get()); } for (Isc isc : iscs) {
	 * ref_bes.add(new Ref_be(project.getNumAppelationProtegee(),
	 * project.getNumPcs(), project.getBonCommande(), isc.getClassification(),
	 * isc.getNumExemplaire(), isc.getNumCopie(), isc.getDateIsc(),
	 * address.getNom(), isc.getTypeSupport(), null, null, project.getNameProject(),
	 * address.getOrganisme())); } createWordDocument(ref_bes);
	 * 
	 * }
	 */

	public void createWordDocument(List<Ref_be> ref_bes) {
		// create a document
		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph entete3 = doc.createParagraph();
		XWPFRun runEntete3 = entete3.createRun();
		entete3.setSpacingAfter(100);
		runEntete3.setText("Ministère des Armées");

		XWPFParagraph entete4 = doc.createParagraph();
		XWPFRun runEntete4 = entete4.createRun();
		entete4.setSpacingAfter(100);
		runEntete4.setText("Société KHIPLUS");

		XWPFParagraph entete = doc.createParagraph();
		XWPFRun runEntete = entete.createRun();
		entete.setSpacingAfter(100);
		// runEntete.setText("Date : " + ref_bes.getDateCreationBe());

		XWPFParagraph entete2 = doc.createParagraph();
		XWPFRun runEntete2 = entete2.createRun();
		entete2.setSpacingAfter(100);
		// String classification = ref_bes.getClassification(); //recupérer la plus
		// haute classification des ISCs : ordre : DR/DRSF/S/SSF
		//runEntete2.setText("Numéro d'enregistrement : " + ref_bes.getidBordereau() + ref_bes.getNameProject()+ ref_bes.getOrganisme() + ref_bes.getDateCreationBe().getYear() + classification);

		///////////////////////////////////////////////////////////////////
		XWPFParagraph title = doc.createParagraph();
		title.setAlignment(ParagraphAlignment.CENTER);
		title.setSpacingBeforeLines(200);
		XWPFRun titleRun = title.createRun();
		titleRun.setText("BORDEREAU DE TRANSMISSION DE SUPPORTS CLASSIFIES");
		titleRun.setUnderline(UnderlinePatterns.SINGLE);
		// titleRun.setBold(true);
		titleRun.setFontFamily("Times New Roman");
		titleRun.setFontSize(11);

		//////////////////////////////////////////////////////// expéditeur
		XWPFParagraph paragraph = doc.createParagraph();
		paragraph.setSpacingBeforeLines(300);
		XWPFRun run = paragraph.createRun();
		paragraph.setSpacingAfter(100);
		run.setText("Service expéditeur :");

		XWPFParagraph paragraph2 = doc.createParagraph();
		paragraph2.setFirstLineIndent(400);
		XWPFRun run2 = paragraph2.createRun();
		run2.setText("KHIPLUS S.A.S.");

		XWPFParagraph paragraph3 = doc.createParagraph();
		paragraph3.setFirstLineIndent(400);
		XWPFRun run3 = paragraph3.createRun();
		run3.setText("66-72 rue Marceau");

		XWPFParagraph paragraph4 = doc.createParagraph();
		paragraph4.setFirstLineIndent(400);
		paragraph4.setSpacingAfter(400);
		XWPFRun run4 = paragraph4.createRun();
		run4.setText("93100 Montreuil");

		///////////////////////////////////////////////////////////// tableau
		XWPFTable table = doc.createTable();
		XWPFTableRow tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText("Référence");
		tableRowOne.addNewTableCell().setText("Classification");
		tableRowOne.addNewTableCell().setText("Nom destinataire");

		List<XWPFTableRow> xwpfTableRows = new ArrayList<>();

		for (int i = 0; i < ref_bes.size(); i++) {
			xwpfTableRows.add(table.createRow());
		}
		for (int i = 0; i < ref_bes.size(); i++) {
			xwpfTableRows.get(i).getCell(0)
					.setText(ref_bes.get(i).getNumAppelationProtegee() + "/" + ref_bes.get(i).getNumPcs() + "/"
							+ ref_bes.get(i).getBonCommande() + '/' + ref_bes.get(i).getTypeSupport() + "/"
							+ ref_bes.get(i).getDateIsc() + "/" + ref_bes.get(i).getNumExemplaire() + "/"
							+ ref_bes.get(i).getNumCopie());
			xwpfTableRows.get(i).getCell(1).setText(ref_bes.get(i).getClassification()); 
			xwpfTableRows.get(i).getCell(2).setText(ref_bes.get(i).getNom());
		}

		XWPFParagraph paragraph19 = doc.createParagraph();
		paragraph19.setSpacingBeforeLines(1000);
		XWPFRun run19 = paragraph19.createRun();
		// run19.setText(ref_bes.get(0).getClassification());

		////////////////////////////////////////////////////////////
		XWPFParagraph paragraph5 = doc.createParagraph();
		paragraph5.setAlignment(ParagraphAlignment.RIGHT);
		paragraph5.setSpacingBeforeLines(200);
		paragraph5.setSpacingAfter(100);
		XWPFRun run5 = paragraph5.createRun();
		run5.setText("Nom, qualité, signature de l'expéditeur et cachet de l'organisme");
		////////////////////////////////////////////////////////////

		XWPFParagraph paragraph6 = doc.createParagraph();
		paragraph6.setSpacingBeforeLines(500);
		XWPFRun run6 = paragraph6.createRun();
		run6.setText("Reçu le :");
		XWPFParagraph paragraph7 = doc.createParagraph();
		XWPFRun run7 = paragraph7.createRun();
		run7.setText("Par :");
		XWPFParagraph paragraph8 = doc.createParagraph();
		XWPFRun run8 = paragraph8.createRun();
		run8.setText("Organisme destinataire :");

		/////////////////////////////////////////////////////////////////
		XWPFParagraph paragraph9 = doc.createParagraph();
		paragraph9.setSpacingBeforeLines(1000);
		XWPFRun run9 = paragraph9.createRun();
		run9.setText("A : à conserver par le destinataire.");
		XWPFParagraph paragraph10 = doc.createParagraph();
		XWPFRun run10 = paragraph10.createRun();
		run10.setText("B : à renvoyer sans délai à l'expéditeur après émargement.");
		XWPFParagraph paragraph11 = doc.createParagraph();
		XWPFRun run11 = paragraph11.createRun();
		run11.setText(
				"B' : à conserver en archives par l'expéditeur jusqu'à réception du feuillet B qui lui sera substitué.");

		// write to a docx file
		FileOutputStream fo = null;
		try {
			// create .docx file
			fo = new FileOutputStream("/home/vto/Downloads/myNewBe.docx");

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

	
}
