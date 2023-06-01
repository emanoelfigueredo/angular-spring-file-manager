package com.efigueredo.file_storage.shared.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteListFilesDto {

    List<String> listaIdFiles;

}
