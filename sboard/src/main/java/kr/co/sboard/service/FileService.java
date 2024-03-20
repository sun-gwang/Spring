package kr.co.sboard.service;

import jakarta.transaction.Transactional;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j @Service @RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;


    @Value("${file.upload.path}")
    private String fileUploadPath;

    public List<FileDTO> fileUpload(ArticleDTO articleDTO){

        // 파일 업로드 시스템 경로 구하기
        String path = new File(fileUploadPath).getAbsolutePath();

        // 파일 정보 리턴을 위한 리스트
        List<FileDTO> files = new ArrayList<>();

        log.info("fileUpload...1");
        
        // 첨부한 파일 갯수만큼 반복 처리
        for(MultipartFile mf : articleDTO.getFiles()){
            log.info("fileUpload...2");

            // 파일 첨부 안하면 에러나기 때문에 if문으로 isEmpty()로 첨부여부 먼저 확인
            if(!mf.isEmpty()){
                log.info("fileUpload...3");
                String oName = mf.getOriginalFilename();

                log.info("fileUpload...4"+ oName);

                String ext   = oName.substring(oName.lastIndexOf("."));
                String sName = UUID.randomUUID().toString() + ext;

                log.info("oName : " + oName);

                try{
                    // 저장
                    mf.transferTo(new File(path, sName));

                    // 파일 정보 생성
                    FileDTO fileDTO = FileDTO.builder()
                                            .oName(oName)
                                            .sName(sName).build();

                    // 리스트 저장
                    files.add(fileDTO);
                }catch (IOException e){
                    log.error("fileUpload : " + e.getMessage());
                }

            }
        }
        // 저장한 파일 정보 리스트 반환
        return files;
    }

    /*
    @Transactional
    public String fileDownload(int fno){

        // 파일 조회
        kr.co.sboard.entity.File file =
    }
     */
}
