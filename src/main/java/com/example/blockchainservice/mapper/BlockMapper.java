package com.example.blockchainservice.mapper;

import com.example.blockchainservice.dtos.BlockDto;
import com.example.blockchainservice.entities.Block;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlockMapper {

        Block BlockDtoToBlock(BlockDto blockDto);
        BlockDto BlockToBlockDto(Block block);


}
