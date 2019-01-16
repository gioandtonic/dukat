package org.jetbrains.dukat.ast.factory

import org.jetbrains.dukat.ast.model.AstNode
import org.jetbrains.dukat.ast.model.ClassDeclaration
import org.jetbrains.dukat.ast.model.ClassLikeDeclaration
import org.jetbrains.dukat.ast.model.Declaration
import org.jetbrains.dukat.ast.model.DocumentRoot
import org.jetbrains.dukat.ast.model.Expression
import org.jetbrains.dukat.ast.model.FunctionDeclaration
import org.jetbrains.dukat.ast.model.FunctionTypeDeclaration
import org.jetbrains.dukat.ast.model.InterfaceDeclaration
import org.jetbrains.dukat.ast.model.MemberDeclaration
import org.jetbrains.dukat.ast.model.MethodDeclaration
import org.jetbrains.dukat.ast.model.ParameterDeclaration
import org.jetbrains.dukat.ast.model.ParameterValue
import org.jetbrains.dukat.ast.model.PropertyDeclaration
import org.jetbrains.dukat.ast.model.TypeDeclaration
import org.jetbrains.dukat.ast.model.TypeParameter
import org.jetbrains.dukat.ast.model.VariableDeclaration

class AstFactory : AstNodeFactory<AstNode> {

    override fun createClassDeclaration(
            name: String,
            members: List<MemberDeclaration>,
            typeParameters: List<TypeParameter>,
            parentEntities: List<ClassLikeDeclaration>
    ) = ClassDeclaration(name, members, typeParameters, parentEntities)

    override fun createInterfaceDeclaration(name: String, members: List<MemberDeclaration>, typeParameters: List<TypeParameter>, parentEntities: List<InterfaceDeclaration>)
        = InterfaceDeclaration(name, members, typeParameters, parentEntities)

    override fun declareVariable(name: String, type: ParameterValue) = VariableDeclaration(name, type)
    override fun declareProperty(
            name: String,
            type: ParameterValue,
            parameters: List<TypeParameter>,
            getter: Boolean,
            setter: Boolean
    ) = PropertyDeclaration(name, type, parameters, getter, setter, false)

    override fun createExpression(kind: TypeDeclaration, meta: String?) = Expression(kind, meta)

    override fun createFunctionDeclaration(
            name: String,
            parameters: Array<ParameterDeclaration>,
            type: ParameterValue,
            typeParameters: Array<TypeParameter>
    ) = FunctionDeclaration(name, parameters.toList(), type, typeParameters.toList())

    override fun createMethodDeclaration(
            name: String,
            parameters: List<ParameterDeclaration>,
            type: ParameterValue,
            typeParameters: List<TypeParameter>,
            override: Boolean,
            operator: Boolean
    ) = MethodDeclaration(name, parameters, type, typeParameters, override, operator)

    override fun createFunctionTypeDeclaration(parameters: Array<ParameterDeclaration>, type: ParameterValue) = FunctionTypeDeclaration(parameters.toList(), type)

    override fun createTypeDeclaration(value: String, params: Array<ParameterValue>) = TypeDeclaration(value, params)

    override fun createParameterDeclaration(name: String, type: ParameterValue, initializer: Expression?) = ParameterDeclaration(name, type, initializer)

    override fun createDocumentRoot(packageName: String, declarations: Array<Declaration>) = DocumentRoot(packageName, declarations.toList())

    override fun createTypeParam(name: String, constraints: Array<ParameterValue>) = TypeParameter(name, constraints.toList())
}